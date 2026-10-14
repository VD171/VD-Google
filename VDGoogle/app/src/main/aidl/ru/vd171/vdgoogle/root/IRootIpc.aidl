package ru.vd171.vdgoogle.root;

interface IRootIpc {
    String readText(String path);
    boolean exists(String path);
    String exec(String cmd);
    List<String> queryDb(String dbPath, String sql);
}
