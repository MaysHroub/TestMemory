package io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import data.Martyr;

public class MartyrInputStream implements AutoCloseable {

	private DataInputStream in;
	
	/**
	 * Constructs a MartyrInputStream object.
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public MartyrInputStream(String fileName) throws FileNotFoundException {
		in = new DataInputStream(new FileInputStream(fileName));
	}
	
	/**
	 * Reads a martyr from the file.
	 * @return
	 * @throws IOException
	 */
	public Martyr readMartyr() throws IOException {
		String name = in.readUTF();
		String date = in.readUTF();
		Martyr martyr = new Martyr(name, date);
		return martyr;
	}
	
	/**
	 * Reads all martyrs from the file.
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Martyr> readMartyrs() throws IOException {
		ArrayList<Martyr> martyrs = new ArrayList<>();
		try {
			while (true) 
				martyrs.add(readMartyr());
			
		} catch (EOFException e) {
			// do nothing
		}
		return martyrs;
	}
	
	@Override
	public void close() throws IOException {
		in.close();
	}

}
