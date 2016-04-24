package com.example.shaunmesias.assignment6.Domain.Person;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class VehicleDetails implements Serializable{
    private final String vehicleType;
    private final String vehicleName;

    public VehicleDetails(String vehicleType, String vehicleName) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
    }


    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleDetails)) return false;

        VehicleDetails that = (VehicleDetails) o;

        if (!vehicleType.equals(that.vehicleType)) return false;
        return vehicleName.equals(that.vehicleName);

    }

    @Override
    public int hashCode() {
        int result = vehicleType.hashCode();
        result = 31 * result + vehicleName.hashCode();
        return result;
    }
}
