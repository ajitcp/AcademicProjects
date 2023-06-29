package com.info6250.finalproject.pojo;

public class Item {
	private String name;
	private String storeName;
    private int count;
    
    
    public Item(String name, String storeName,int count) {
        this.name = name;
        this.count = count;
        this.storeName=storeName;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
    
    

}
