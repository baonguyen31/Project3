package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Long numberOfBasement;
	private String  ward;
	private String  street;
	private String  district;
	private String  direction  ;
	private String  level;
	private String address;
	private Long  floorarea;
	private String  managername  ;
	private String  managerphone  ;
	private Long rentAreaFrom;
	private Long rentAreaTo;
	private Long rentPriceFrom;
	private Long rentPriceTo;
	private List<String> typeCode = new ArrayList<>();
	private Long staffid;
	

	
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.numberOfBasement = builder.numberOfBasement;
		this.address = builder.address;
		this.floorarea = builder.floorarea;
		this.ward= builder.ward;
		this.street = builder.street;
		this.district = builder.district;
		this.level = builder.level;
		this.direction = builder.direction;
		this.managername = builder.managername;
		this.managerphone = builder.managerphone;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.typeCode = builder.typeCode;
		this.staffid = builder.staffid;
	}

	public String getName() {
		return name;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getAddress() {
		return address;
	}
	public Long getFloorarea() {
		return floorarea;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}


	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getDistrict() {
		return district;
	}

	public String getManagername() {
		return managername;
	}
	public String getManagerphone() {
		return managerphone;
	}
	public Long getRentAreaFrom() {
		return rentAreaFrom;
	}
	public Long getRentAreaTo() {
		return rentAreaTo;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Long getRentPriceTo() {
		return rentPriceTo;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public Long getStaffid() {
		return staffid;
	}
	
	public static class Builder {
		private String name;
		private Long numberOfBasement;
		private String address;
		private Long  floorarea;
		private String  ward;
		private String  street;
		private String  district  ;
		private String  direction  ;
		private String  level;
		private String  managername  ;
		private String  managerphone  ;
		private Long rentAreaFrom;
		private Long rentAreaTo;
		private Long rentPriceFrom;
		private Long rentPriceTo;
		private List<String> typeCode = new ArrayList<>();
		private Long staffid;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setNumberOfBasement(Long numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		public Builder setFloorarea(Long floorarea) {
			this.floorarea = floorarea;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		
		
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;

		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;

		}
		public Builder setManagername(String managername) {
			this.managername = managername;
			return this;
		}
		public Builder setManagerphone(String managerphone) {
			this.managerphone = managerphone;
			return this;
		}
		public Builder setRentAreaFrom(Long rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}
		public Builder setRentAreaTo(Long rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}
		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public Builder setStaffid(Long staffid) {
			this.staffid = staffid;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
			
	}
	
}
