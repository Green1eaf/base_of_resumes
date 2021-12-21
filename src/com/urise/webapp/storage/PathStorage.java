package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private SaveStrategy strategy;

    protected PathStorage(String dir, SaveStrategy strategy) {
        this.strategy = strategy;

        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesPaths().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesPaths().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path: " + path, path.toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error ", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error: ", path.toString());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Path> list = getFilesPaths().toList();
        List<Resume> result = new ArrayList<>(list.size());
        for (Path path : list) {
            result.add(doGet(path));
        }
        return result;
    }

    private Stream<Path> getFilesPaths() {
        Stream<Path> listPaths;
        try {
            listPaths = Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return listPaths;
    }
}