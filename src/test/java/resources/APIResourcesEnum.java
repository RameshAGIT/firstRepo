package resources;

public enum APIResourcesEnum {
	//enum is a special class in java which has collection of Constants or Methods
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	
	APIResourcesEnum(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
		
	}
}
