package com.inventage.nexusaptplugin.cache.generators;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import javax.inject.Inject;
import javax.inject.Named;

import com.inventage.nexusaptplugin.cache.DebianFileManager;
import com.inventage.nexusaptplugin.cache.FileGenerator;
import com.inventage.nexusaptplugin.cache.RepositoryData;


@Named
public class PackagesGzGenerator
        implements FileGenerator {
    private final DebianFileManager fileManager;

    @Inject
    public PackagesGzGenerator(DebianFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public byte[] generateFile(RepositoryData data)
            throws Exception {
        byte[] packages = fileManager.getFile("Packages", data);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gz = new GZIPOutputStream(baos);

        gz.write(packages);

        gz.close();

        return baos.toByteArray();
    }

}
