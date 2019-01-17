package ua.com.deluxedostavka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import ua.com.deluxedostavka.dto.goods.GoodsRequest;
import ua.com.deluxedostavka.dto.goods.GoodsResponse;
import ua.com.deluxedostavka.dto.other.BASE64DecodedMultipartFile;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.entity.Goods;
import ua.com.deluxedostavka.entity.SubCategory;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.repository.GoodsRepository;
import ua.com.deluxedostavka.repository.SubCategoryRepository;
import ua.com.deluxedostavka.service.GoodsService;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Override
    public List<GoodsResponse> getAll() {
        return goodsRepository.findAll().stream().map(GoodsResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<GoodsResponse> getAllBySubCategory(Long idSubCategory) {
        return goodsRepository.findAllBySubCategoryId(idSubCategory).stream().map(GoodsResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<GoodsResponse> getAllByCategory(Long idCategory) {
        return goodsRepository.findAllByCategory(idCategory).stream().map(GoodsResponse::new).collect(Collectors.toList());
    }

    public DataResponse<GoodsResponse> getPage(PaginationRequest paginationRequest) {
        Page<Goods> page = goodsRepository.findAll(paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
    }

    public DataResponse<GoodsResponse> getPageByCategoryId(Long id, PaginationRequest paginationRequest) {
        Page<Goods> page = goodsRepository.findAllByCategoryId(id, paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
    }

    public DataResponse<GoodsResponse> getPageBySubCategoryId(Long id, PaginationRequest paginationRequest) {
        Page<Goods> page = goodsRepository.findAllBySubCategoryId(id, paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public DataResponse<GoodsResponse> findByName(String name, PaginationRequest paginationRequest) {
        Page<Goods> page = goodsRepository.findAllByNameLike(name, paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public GoodsResponse getOne(Long idGoods) {
        return new GoodsResponse(goodsRepository.findOne(idGoods));
    }

    @Override
    @Transactional
    public GoodsResponse create(GoodsRequest goodsRequest) throws IOException, ObjectNotFoundException {
        String img = goodsRequest.getImg();
        Goods goods = new Goods();
        goods = createOrUpdateGoods(goods,goodsRequest);
        return new GoodsResponse(saveFile(createMultipartFile(goods ,img)));
    }

    @Override
    @Transactional
    public GoodsResponse update(Long id, GoodsRequest goodsRequest) throws ObjectNotFoundException, IOException {
        String img = goodsRequest.getImg();
        Goods goods = findOne(id);
        goods = createOrUpdateGoods(goods,goodsRequest);
//        if(img != null){
//            return new GoodsResponse(saveFile(createMultipartFile(goods ,img)));
//        }else{
//            return new GoodsResponse(goods);
//        }
            return new GoodsResponse(goods);
    }

    @Override
    public void delete(Long id) throws ObjectNotFoundException {
        Goods goods = findOne(id);
        File file = new File("/home/admin/web/aa/public_html/resources/" + goods.getPathImage());
        file.delete();
        goodsRepository.delete(id);
    }

    private BASE64DecodedMultipartFile createMultipartFile(Goods goods,String img) throws IOException {
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(img.split(",")[1]);
        String expansion = img.split(",")[0].split("/")[1].split(";")[0];
        return new BASE64DecodedMultipartFile(imageByte, expansion, String.valueOf(goods.getId()));
    }

    private Goods createOrUpdateGoods(Goods goods, GoodsRequest request) throws ObjectNotFoundException {
        goods.setName(request.getName());
        goods.setDescription(request.getDescription());
        goods.setPrice(request.getPrice());
        goods.setWeight(request.getWeight());
        goods.setSubCategory(findSubCategory(request.getSubCategoryId()));
        return goodsRepository.saveAndFlush(goods);
    }

    private SubCategory findSubCategory(Long id) throws ObjectNotFoundException {
        SubCategory subCategory = subCategoryRepository.findOne(id);
        if (subCategory != null) {
            return subCategory;
        } else {
            throw new ObjectNotFoundException("SubCategory with id :" + id + " not found");
        }
    }

    private Goods saveFile(MultipartFile file) {
        File pathToFolder = new File("/home/admin/web/aa/public_html/resources/");
        createFolder(pathToFolder);
        Goods goods = goodsRepository.findOne(Long.valueOf(file.getName()));
        File pathToFile = new File(pathToFolder + "/" + file.getOriginalFilename());
        writeFile(pathToFile, file);
        goods.setPathImage(file.getOriginalFilename());
        return goodsRepository.save(goods);
    }

    private void createFolder(File pathToFolder) {
        if (!pathToFolder.exists())
            pathToFolder.mkdirs();
    }



    private void writeFile(File PathToFile, MultipartFile file) {
        try (OutputStream fos = new FileOutputStream(PathToFile); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(file.getBytes(), 0, file.getBytes().length);
            bos.flush();
        } catch (IOException e) {

        }
    }

    private Goods findOne(Long id) throws ObjectNotFoundException {
        Goods goods = goodsRepository.findOne(id);
        if(goods != null){
            return goods;
        }else{
            throw new ObjectNotFoundException("Goods with id : "+id+" not found !!!");
        }
    }
}
