package org.seydaliev.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.seydaliev.model.Device;
import org.seydaliev.model.Model;
import org.seydaliev.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Operation(
            summary = "Поиск и фильтрация устройств",
            description = "Позволяет искать, фильтровать и сортировать устройства по различным критериям."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Устройства успешно найдены"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    @GetMapping("/search")
    public List<Device> searchProducts(
            @RequestParam String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sortBy) {
        return deviceService.searchAndFilterAndSortProducts(name, type, color, minPrice, maxPrice, sortBy);
    }

    @Operation(
            summary = "Добавление нового продукта",
            description = "Позволяет добавить новое устройство в систему."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для добавления устройства")
    })
    @PostMapping("/addProduct")
    public Device addNewTechProduct(@RequestBody Device device) {
        return deviceService.addNewTechProduct(device);
    }

    @Operation(
            summary = "Добавление новой модели для существующего устройства",
            description = "Позволяет добавить модель к уже существующему устройству по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Модель успешно добавлена"),
            @ApiResponse(responseCode = "404", description = "Устройство с таким идентификатором не найдено"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для добавления модели")
    })
    @PostMapping("/addModel/{deviceId}")
    public Model addNewModel(@PathVariable Long deviceId, @RequestBody Model newModel) {
        return deviceService.addNewModel(deviceId, newModel);
    }
}

