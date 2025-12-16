package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    @Autowired
    private FileStorageService fileStorage;

    public List<Model_Producto> obtenerTodos() { return repo.findAll(); }

    public Model_Producto obtenerPorId(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public Model_Producto agregar(Model_Producto p, MultipartFile img, MultipartFile imgNutricional) {
        if(img != null && !img.isEmpty()) {
            String fileName = fileStorage.storeFile(img);
            p.setImg(fileName);
        }
        if (imgNutricional != null && !imgNutricional.isEmpty()) {
            String fileNameNutri = fileStorage.storeFile(imgNutricional);
            p.setImgNutricional(fileNameNutri);
        }
        return repo.save(p);
    }

    public Model_Producto actualizar(int id, Model_Producto p,MultipartFile img, MultipartFile imgNutricional) {
        Model_Producto actual = obtenerPorId(id);
        actual.setNombre(p.getNombre());
        actual.setPrecio(p.getPrecio());
        actual.setStock(p.getStock());
        actual.setExpirationDate(p.getExpirationDate());
        if (img != null && !img.isEmpty()) {
            // opcional: fileStorage.deleteFile(actual.getImg());
            String fileName = fileStorage.storeFile(img);
            actual.setImg(fileName);
        }

        if (imgNutricional != null && !imgNutricional.isEmpty()) {
            // opcional: fileStorage.deleteFile(actual.getImgNutricional());
            String fileNameNutri = fileStorage.storeFile(imgNutricional);
            actual.setImgNutricional(fileNameNutri);
        }
        return repo.save(actual);
    }

    public Model_Producto actualizar(int id, Model_Producto p) {
        return actualizar(id, p, null, null);
    }

    public String eliminar(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Producto eliminado";
        } else return "No encontrado";
    }
}
