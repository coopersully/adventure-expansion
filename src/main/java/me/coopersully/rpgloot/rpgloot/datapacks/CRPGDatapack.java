package me.coopersully.rpgloot.rpgloot.datapacks;

import java.io.File;

public class CRPGDatapack {

    public File folder;
    public File marker;
    public float version;

    public CRPGDatapack(File folder, File marker, float version) {
        this.folder = folder;
        this.marker = marker;
        this.version = version;
    }

    @Override
    public String toString() {
        return "[" + this.folder.getPath() + ", " + this.marker.getPath() + ", " + this.version + "]";
    }

}
