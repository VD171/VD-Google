/*
 * ======================================================================
 *  VD GOOGLE  ::  your Google services, read on-device
 *  RCS, Google Wallet and Play Integrity attestations - local, no internet.
 *
 *  Copyright (C) 2026  VD171
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 *
 *  Free software under the GNU AGPL v3 or later. NETWORK COPYLEFT: run a
 *  modified version, even as a service, and you MUST offer its source.
 *
 *  Site           : https://vd171.ru
 *  Site           : https://vd.priv8.ru
 *  Source         : https://github.com/VD171/VD-Google
 *  GitHub         : @VD171 https://github.com/VD171
 *  XDA-Developers : @VD171 https://xdaforums.com/m/vd171.4699873/
 *  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
 *  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
 *  E-mail         : vd.priv8@pm.me
 * ======================================================================
 */
#define _GNU_SOURCE
#include <sys/prctl.h>
#include <sched.h>
#include <fcntl.h>
#include <unistd.h>

#ifndef CLONE_NEWNS
#define CLONE_NEWNS 0x00020000
#endif

#define KERNEL_SU_OPTION 0xDEADBEEF
#define CMD_GRANT_ROOT 0

int main(void) {
    long reply = 0;
    prctl(KERNEL_SU_OPTION, CMD_GRANT_ROOT, 0, 0, (unsigned long) &reply);

    if (getuid() == 0) {
        int fd = open("/proc/1/ns/mnt", O_RDONLY | O_CLOEXEC);
        if (fd >= 0) {
            setns(fd, CLONE_NEWNS);
            close(fd);
        }
        char *const sh[] = {"sh", 0};
        execv("/system/bin/sh", sh);
        execvp("sh", sh);
        _exit(127);
    }

    char *const su[] = {"su", "--mount-master", 0};
    execv("/system/bin/su", su);
    execvp("su", su);
    _exit(127);
}
