package io.litedb.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import lombok.Getter;

/** The block numbers in a file range from 0 up to number_of_blocks - 1 */
public class LiteFile {
    public static final int BLOCK_SIZE = 4096;

    private @Getter File file;
    private RandomAccessFile raf;

    public LiteFile(String fileName) throws IOException {
        this.file = new File(fileName + ".lt");
        // If new file is created, append an empty block
        if (this.file.createNewFile() || this.file.length() == 0) {
            appendNewBlock(0);
        }
        this.raf = new RandomAccessFile(file, "rws");
    }

    /**
     * Appends a new block of 0's to the file
     */
    private void appendNewBlock(int blockNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "w");
        raf.seek(blockNumber * BLOCK_SIZE);
        byte[] emptyBytes = new byte[BLOCK_SIZE];
        Arrays.fill(emptyBytes, (byte) 0);
        raf.write(emptyBytes);
        raf.close();
    }

    /**
     * Read the byte contents of a given block to a logical page
     */
    public LitePage readBlock(BlockIdentifier id) {
        validateBlock(id);

        try {
            raf.seek(id.getBlockNumber() * id.getBlockSize());
            byte[] contents = new byte[id.getBlockSize()];
            raf.read(contents);
            return new LitePage(contents);
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                "Failed to get block %d contents: %s", id.getBlockNumber(), e.getMessage()
                )
            );
        }
    }

    /**
     * Write the byte contents of a page to a given block of memory
     */
    public void writeBlock(BlockIdentifier id, LitePage page) {
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

    public void validateBlock(BlockIdentifier id) {
        if (!id.getFileName().equals(this.file.getName())) {
            throw new RuntimeException("File names do not match. This is not supposed to happen");
        }

        if (id.getBlockNumber() >= getBlockCount() || id.getBlockNumber() < 0) {
            throw new RuntimeException(String.format("Invalid block number. Number of blocks in file: %d", getBlockCount()));
        }
    }
}
