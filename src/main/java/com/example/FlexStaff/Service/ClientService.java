package com.example.FlexStaff.Service;

import com.example.FlexStaff.DAO.ClientRepo;
import com.example.FlexStaff.Entities.Client;
import com.example.FlexStaff.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Client uploadImage(MultipartFile file, Client cImage) throws IOException {


        cImage.setProfileImgName(file.getOriginalFilename());

        cImage.setProfileImgData(ImageUtil.compressImage(file.getBytes()));
        return clientRepo.save(cImage);
    }

    public byte[] downloadImage(Client imageData){

        return ImageUtil.decompressImage(imageData.getProfileImgData());
    }

}
