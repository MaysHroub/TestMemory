package io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import data.Martyr;

public class MartyrOutputStream implements AutoCloseable {

	private DataOutputStream out;

	/**
	 * Constructs a MartyrOutputStream object.
	 * @param fileName
	 * @param append
	 * @throws FileNotFoundException
	 */
	public MartyrOutputStream(String fileName, boolean append) throws FileNotFoundException {
		out = new DataOutputStream(new FileOutputStream(fileName, append));
	}

	/**
	 * Writes a given martyr to the file.
	 * @param martyr
	 * @throws IOException
	 */
	public void writeMartyr(Martyr martyr) throws IOException {
		out.writeUTF(martyr.getName());
		out.writeUTF(martyr.getDateOfMartyrdom());
	}

	/**
	 * Writes a list of martyrs to the file.
	 * @param martyrs
	 * @throws IOException
	 */
	public void writeMartyrs(ArrayList<Martyr> martyrs) throws IOException {
		for (Martyr martyr : martyrs) 
			writeMartyr(martyr);
	}

	@Override
	public void close() throws IOException {
		out.close();
	}
}
