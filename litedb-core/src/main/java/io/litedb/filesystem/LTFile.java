package io.litedb.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import lombok.Getter;

/** The block numbers in a file range from 0 up to number_of_blocks - 1 */
public class LTFile implements LTDBFile {
    public static final int BLOCK_SIZE = 4096;

    private @Getter File file;
    private RandomAccessFile raf;

    public LTFile(String fileName) throws IOException {
        this.file = new File(fileName);
        // If new file is created, append an empty block
        if (this.file.createNewFile() || this.file.length() == 0) {
            appendNewBlock(0);
        }
        if (this.raf == null) {
            raf = new RandomAccessFile(file, "rws");
        }
    }

    /**
     * Appends a new page of 0's to the file
     */
    public void appendNewBlock(int blockNumber) throws IOException {
        if (raf == null) {
            raf = new RandomAccessFile(file, "rws");
        }
        raf.seek(blockNumber * BLOCK_SIZE);
        byte[] emptyBytes = new byte[BLOCK_SIZE];
        Arrays.fill(emptyBytes, (byte) 0);
        raf.write(emptyBytes);
    }

    /**
     * Read the byte contents of a given block to a logical page
     */
    public LTDBPage readBlock(LTBlockIdentifier id) {
        validateBlock(id);

        try {
            raf.seek(id.getBlockNumber() * id.getBlockSize());
            byte[] contents = new byte[id.getBlockSize()];
            raf.read(contents);
            return new LTPage(contents);
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                "Failed to get block %d contents: %s", id.getBlockNumber(), e.getMessage()
                )
            );
        }
    }

    public LTDBPage readBlock(int id) throws IOException {
        LTBlockIdentifier block = new LTBlockIdentifier(this.file.getName(), id, BLOCK_SIZE);
        return readBlock(block);
    }

    /**
     * Write the byte contents of a page to a given block of memory
     */
    public void writeBlock(LTBlockIdentifier id, LTDBPage page) {
        validateBlock(id);

        try {
            raf.seek(id.getBlockNumber() * id.getBlockSize());
            raf.write(page.getContents());
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                "Failed to write block %d contents: %s", id.getBlockNumber(), e.getMessage()
                )
            );
        }
    }

    public void writeBlock(int id, LTDBPage page) throws IOException {
        LTBlockIdentifier block = new LTBlockIdentifier(this.file.getName(), id, BLOCK_SIZE);
        writeBlock(block, page);
    }

    /**
     * Close the file to release all system resources held by the file
     */
    public void close() {
        try {
            raf.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to close file due to: %s", e.getMessage()));
        }
    }

    /**
     * Get the number of blocks in the file
     */
    public long getBlockCount() {
        return this.file.length() / BLOCK_SIZE;
    }

    public void validateBlock(LTBlockIdentifier id) {
        if (!id.getFileName().equals(this.file.getName())) {
            throw new RuntimeException("File names do not match. This is not supposed to happen");
        }

        if (id.getBlockNumber() >= getBlockCount() || id.getBlockNumber() < 0) {
            throw new RuntimeException(String.format("Invalid block number. Number of blocks in file: %d", getBlockCount()));
        }
    }

    public String getFileName() {
        return this.file.getName();
    }
}
