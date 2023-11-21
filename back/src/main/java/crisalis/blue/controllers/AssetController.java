package crisalis.blue.controllers;

import crisalis.blue.models.dto.AssetDTO;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.services.AssetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asset")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService itemsService) {
        this.assetService = itemsService;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetDTO create(@RequestBody AssetDTO assetDTO) {
        return assetService.create(assetDTO);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssetDTO> read() {
        return assetService.read();
    }

    @GetMapping(value = "read", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetDTO getAssetById(@RequestParam Long id) {
        return this.assetService.getAssetById(id);
    }

    @GetMapping(value = "readType", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssetDTO> readType(@RequestParam String type) {
        return assetService.readType(type);
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetDTO update(@RequestBody AssetDTO assetDTO) {
        return assetService.update(assetDTO);
    }

    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestParam Long id) {
        assetService.delete(id);
    }
}
