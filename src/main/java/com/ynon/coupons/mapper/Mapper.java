package com.ynon.coupons.mapper;


import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ynon on  15-Aug-21
 */
public interface Mapper<DAO,DTO> {

    DAO toDao(DTO dto) throws ApplicationException;
    DTO toDto(DAO dao);
    List<DAO> toDaoList(List<DTO> dtos) throws ApplicationException;
    List<DTO> toDtoList(List<DAO> daos);

}
