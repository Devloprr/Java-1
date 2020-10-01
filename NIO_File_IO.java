import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

class NIO_File_IO{
	public static void main(String ar[]){
		Path file1 = Paths.get("Sample.txt");
		Path file2 = Paths.get("Copying.txt");
		try(SeekableByteChannel from = Files.newByteChannel(file1, StandardOpenOption.READ);
			SeekableByteChannel to = Files.newByteChannel(file2, StandardOpenOption.CREATE,
						StandardOpenOption.WRITE, StandardOpenOption.APPEND);){

			ByteBuffer bb = ByteBuffer.allocate((int)from.size()+2);
			from.read(bb);
			bb.putChar('\n');
			bb.rewind();
			to.write(bb);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			System.out.println("Data Copied To File");
		}
	}
}