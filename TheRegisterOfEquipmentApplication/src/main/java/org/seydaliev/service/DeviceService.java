package org.seydaliev.service;

import org.seydaliev.model.Device;
import org.seydaliev.model.Model;

import java.util.List;

public interface DeviceService {
     List<Device> searchAndFilterAndSortProducts(String name, String type, String color, Double minPrice, Double maxPrice, String sortBy);
     List<Device> filterProducts(List<Device> products, String type, String color, Double minPrice, Double maxPrice);
     List<Device> sortByName(List<Device> products);
     List<Device> sortByPrice(List<Device> products);
     Model addNewModel(Long techProductId, Model newModel);
     Device addNewTechProduct(Device device);

}
