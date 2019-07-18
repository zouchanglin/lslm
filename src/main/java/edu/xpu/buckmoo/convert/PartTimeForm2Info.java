package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.form.PartTimeForm;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 兼职表单转为兼职信息对象
 */
public class PartTimeForm2Info {
    public static PartInfo form2partInfo(PartTimeForm partTimeForm){
        PartInfo partInfo = new PartInfo();
        BeanUtils.copyProperties(partTimeForm, partInfo);

        partInfo.setPartStart(new Date(partTimeForm.getPartStart()));
        partInfo.setPartEnd(new Date(partTimeForm.getPartEnd()));
        partInfo.setPartId(KeyUtil.genUniqueKey());

        return partInfo;
    }
}
