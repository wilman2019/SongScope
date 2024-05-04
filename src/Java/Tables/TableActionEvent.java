package Java.Tables;


public interface TableActionEvent {
    public void playlistAdd(int row);
    public void playlistRemove(int row);
    public void compareTableAdd(int row);
    public void removeFromPlaylist(int row);
}
