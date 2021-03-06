package com.qudiancan.backend.service.util.shop;

import com.qudiancan.backend.enums.ResponseEnum;
import com.qudiancan.backend.exception.ShopException;
import com.qudiancan.backend.pojo.vo.shop.BranchVO;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author NINGTIANMIN
 */
public class ShopBranchServiceUtil {
    public static final Pattern LONGITUDE_PATTERN = Pattern.compile("^-?((0|1?[0-7]?[0-9]?)(([.][0-9]{1,10})?)|180(([.][0]{1,10})?))$");
    public static final Pattern LATITUDE_PATTERN = Pattern.compile("^-?((0|[1-8]?[0-9]?)(([.][0-9]{1,10})?)|90(([.][0]{1,10})?))$");

    public static void checkBranchVO(BranchVO branchVO) {
        if (branchVO == null) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG);
        }
        if (StringUtils.isEmpty(branchVO.getAddress())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "address");
        }
        if (StringUtils.isEmpty(branchVO.getIntroduction())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "introduction");
        }
        if (!checkLatitudeValidity(branchVO.getLatitude())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "latitude");
        }
        if (!checkLongitudeValidity(branchVO.getLongitude())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "longitude");
        }
        if (StringUtils.isEmpty(branchVO.getName())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "name");
        }
        if (StringUtils.isEmpty(branchVO.getNotice())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "notice");
        }
        if (!ShopAccountServiceUtil.checkPhoneValidity(branchVO.getPhone())) {
            throw new ShopException(ResponseEnum.SHOP_PARAM_WRONG, "phone");
        }
    }

    public static boolean checkLongitudeValidity(String longitude) {
        return !StringUtils.isEmpty(longitude) && LONGITUDE_PATTERN.matcher(longitude).matches();
    }

    public static boolean checkLatitudeValidity(String latitude) {
        return !StringUtils.isEmpty(latitude) && LATITUDE_PATTERN.matcher(latitude).matches();
    }
}
