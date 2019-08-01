package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.form.ActivityForm;

/**
 * @author tim
 * @version 1.0
 * @className ActivityForm2ActivityInfo
 * @description 活动页面表单转为活动信息对象
 * @date 2019-06-22 10:57
 */
public class ActivityForm2ActivityInfo {

    public static void activityForm2ActivityInfo(ActivityInfo activityInfo, ActivityForm activityForm){
        String activityName = activityForm.getActivityName();
        if(activityName != null) activityInfo.setActivityName(activityName);

        String activityMain = activityForm.getActivityMain();
        if(activityMain != null) activityInfo.setActivityMain(activityMain);

        String activityAddress = activityForm.getActivityAddress();
        if(activityAddress != null) activityInfo.setActivityAddress(activityAddress);

        Long activityStart = activityForm.getActivityStart();
        if(activityStart != null) activityInfo.setActivityStart(activityStart);

        Long activityEnd = activityForm.getActivityEnd();
        if(activityEnd != null) activityInfo.setActivityEnd(activityEnd);

        Integer activityMax = activityForm.getActivityMax();
        if(activityMax != null) activityInfo.setActivityMax(activityMax);

        Integer activityMode = activityForm.getActivityMode();
        if(activityMode != null) activityInfo.setActivityMode(activityMode);

        Integer activityGeneralize = activityForm.getActivityGeneralize();
        if(activityGeneralize != null) activityInfo.setActivityGeneralize(activityGeneralize);

        String activityLink = activityForm.getActivityLink();
        if(activityLink != null) activityInfo.setActivityLink(activityLink);

        String activityAbstract = activityForm.getActivityAbstract();
        if(activityAbstract != null) activityInfo.setActivityAbstract(activityAbstract);

        String activityLogo = activityForm.getActivityLogo();
        if(activityLogo != null) activityInfo.setActivityLogo(activityLogo);
    }
}
