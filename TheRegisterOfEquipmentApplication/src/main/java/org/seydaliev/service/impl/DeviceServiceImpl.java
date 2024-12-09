package org.seydaliev.service.impl;

import org.seydaliev.model.*;
import org.seydaliev.repository.*;
import org.seydaliev.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TelevisionRepository tvRepository;

    @Autowired
    private VacuumCleanerRepository vacuumCleanerRepository;

    @Autowired
    private RefrigeratorRepository fridgeRepository;

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Autowired
    private ComputerRepository computerRepository;

    // Поиск, фильтрация и сортировка
    public List<Device> searchAndFilterAndSortProducts(String name, String type, String color, Double minPrice, Double maxPrice, String sortBy) {
        // Поиск по наименованию
        List<Device> products = deviceRepository.findByNameIgnoreCaseContaining(name);

        // Фильтрация
        products = filterProducts(products, type, color, minPrice, maxPrice);

        // Сортировка по нужному параметру
        if ("name".equalsIgnoreCase(sortBy)) {
            return sortByName(products);
        } else if ("price".equalsIgnoreCase(sortBy)) {
            return sortByPrice(products);
        } else {
            return products;  // если не указан параметр сортировки, возвращаем без изменений
        }
    }

    // Фильтрация по атрибутам (как ранее)
    public List<Device> filterProducts(List<Device> products, String type, String color, Double minPrice, Double maxPrice) {
        return products.stream()
                .filter(p -> (type == null || p.getClass().getSimpleName().equalsIgnoreCase(type))) // Фильтрация по типу
                .filter(p -> (color == null || p.getModels().stream().anyMatch(m -> m.getColor().equalsIgnoreCase(color)))) // Фильтрация по цвету
                .filter(p -> (minPrice == null && maxPrice == null) ||
                        p.getModels().stream().anyMatch(m ->
                                (minPrice == null || m.getPrice() >= minPrice) &&
                                        (maxPrice == null || m.getPrice() <= maxPrice))) // Фильтрация по цене
                .collect(Collectors.toList());
    }


    // Сортировка по наименованию
    public List<Device> sortByName(List<Device> products) {
        return products.stream()
                .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                .collect(Collectors.toList());
    }

    // Сортировка по цене
    public List<Device> sortByPrice(List<Device> products) {
        return products.stream()
                .sorted((p1, p2) -> Double.compare(p1.getModels().get(0).getPrice(), p2.getModels().get(0).getPrice()))
                .collect(Collectors.toList());
    }

    // Добавление новой модели
    public Model addNewModel(Long deviceId, Model newModel) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Tech product not found"));

        // Связываем модель с продуктом
        newModel.setDevice(device);

        // Сохранение модели в базе данных
        if (newModel instanceof Television) {
            tvRepository.save((Television) newModel);
        } else if (newModel instanceof VacuumCleaner) {
            vacuumCleanerRepository.save((VacuumCleaner) newModel);
        } else if (newModel instanceof Refrigerator) {
            fridgeRepository.save((Refrigerator) newModel);
        } else if (newModel instanceof Smartphone) {
            smartphoneRepository.save((Smartphone) newModel);
        } else if (newModel instanceof Computer) {
            computerRepository.save((Computer) newModel);
        }

        return newModel;
    }

    // Добавление новой позиции в реестр (новый тип техники)
    public Device addNewTechProduct(Device device) {
        return deviceRepository.save(device);
    }
}

