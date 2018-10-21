package utils;

import com.example.product.vo.ResultVO;

public class ResultVOUtil {


    public static ResultVO success(Object object){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }


}
