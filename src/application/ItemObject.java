package application;

public class ItemObject {
	private int id;
	private String item;
	private int price;
	
	public ItemObject(String item, int price) {
		this.item = item;
		this.price = price;
	}
	

	public ItemObject(int id, String item, int price) {
	
		this.id = id;
		this.item = item;
		this.price = price;
	}





	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getItem() {
		return item;
	}



	public void setItem(String item) {
		this.item = item;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}
	
	


}
