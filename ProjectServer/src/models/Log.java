package models;

import java.util.ArrayList;

public class Log {
	private ArrayList<Entry> entries;

	public Log() {
		super();
	}
	
	public void addEntry(Entry e) {
		this.entries.add(e);
	}

	public ArrayList<Entry> visualizzaLog() {
		return entries;
	}

	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
}
