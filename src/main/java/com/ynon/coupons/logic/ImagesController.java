package com.ynon.coupons.logic;

import com.ynon.coupons.dao.IImageDao;
import com.ynon.coupons.entities.Image;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImagesController {

    private final IImageDao imageDao;

    public byte[] findImage(UUID uuid) throws ApplicationException {
        if(!isUUIDExist(uuid)){
            throw new ApplicationException(ErrorType.ID_NOT_EXIST,ErrorType.ID_NOT_EXIST.getErrorMessage());
        }

        return imageDao.findImageByUUID(uuid);
    }


    public boolean isUUIDExist(UUID uuid) {
        return imageDao.existsById(uuid);
    }

    public UUID addImage(byte[] bytes) throws ApplicationException {
        Image image=null;
        UUID uuid =null;
        try {

            image = new Image(bytes);
            uuid = imageDao.save(image).getId();
        }catch (Exception e){
            throw new ApplicationException(ErrorType.IMAGE_UPLOAD_FAILED,ErrorType.IMAGE_UPLOAD_FAILED.getErrorMessage());
        }
        return uuid;
    }


    public Image getImage(UUID uuid) throws ApplicationException {
        if(!imageDao.existsById(uuid)){
            throw new ApplicationException(ErrorType.IMAGE_ID_NOT_FOUND,ErrorType.IMAGE_ID_NOT_FOUND.getErrorMessage());
        }
        return imageDao.findById(uuid).orElse(new Image());
    }
}
