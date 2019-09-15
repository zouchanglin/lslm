package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.form.PartTimeForm;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 兼职表单转为兼职信息对象
 */
@Slf4j
public class PartTimeForm2Info {
    public static PartInfo form2partInfo(PartTimeForm partTimeForm){
        log.info("[PartTimeForm2Info] partTimeForm={}", partTimeForm);
        PartInfo partInfo = new PartInfo();
        BeanUtils.copyProperties(partTimeForm, partInfo);
        partInfo.setPartStart(partTimeForm.getPartStart());
        partInfo.setPartEnd(partTimeForm.getPartEnd());
        partInfo.setPartId(KeyUtil.genUniqueKey());
        log.info("[PartTimeForm2Info] partInfo={}", partInfo);
        return partInfo;
    }
}
