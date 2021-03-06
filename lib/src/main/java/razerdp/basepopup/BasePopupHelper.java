package razerdp.basepopup;

import android.animation.Animator;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import razerdp.library.R;

/**
 * Created by 大灯泡 on 2017/12/12.
 * <p>
 * popupoption
 */
final class BasePopupHelper {
    //是否自动弹出输入框(default:false)
    private boolean autoShowInputMethod = false;

    //anima
    private Animation mShowAnimation;
    private Animator mShowAnimator;
    private Animation mExitAnimation;
    private Animator mExitAnimator;

    //callback
    private BasePopupWindow.OnDismissListener mOnDismissListener;
    private BasePopupWindow.OnBeforeShowCallback mOnBeforeShowCallback;

    //option
    private int popupGravity = Gravity.NO_GRAVITY;
    private int offsetX;
    private int offsetY;
    private int popupViewWidth;
    private int popupViewHeight;
    //锚点view的location
    private int[] mAnchorViewLocation;
    //是否自动适配popup的位置
    private boolean isAutoLocatePopup;
    //showasdropdown
    private boolean showAtDown;
    //点击popup外部是否消失
    private boolean dismissWhenTouchOutside;
    //是否需要淡入window动画
    private volatile boolean needPopupFadeAnima = true;

    private int mPopupLayoutId;

    private boolean backPressEnable = true;


    //popup params
    private boolean focusable = true;
    private boolean outsideTouchable = false;

    BasePopupHelper() {
        mAnchorViewLocation = new int[2];
    }

    int getPopupLayoutId() {
        return mPopupLayoutId;
    }

    BasePopupHelper setPopupLayoutId(int popupLayoutId) {
        mPopupLayoutId = popupLayoutId;
        return this;
    }

    Animation getShowAnimation() {
        return mShowAnimation;
    }

    BasePopupHelper setShowAnimation(Animation showAnimation) {
        if (mShowAnimation == showAnimation) return this;
        if (mShowAnimation != null) {
            mShowAnimation.cancel();
        }
        mShowAnimation = showAnimation;
        return this;
    }

    Animator getShowAnimator() {
        return mShowAnimator;
    }

    BasePopupHelper setShowAnimator(Animator showAnimator) {
        if (mShowAnimator == showAnimator) return this;
        if (mShowAnimator != null) {
            mShowAnimator.cancel();
        }
        mShowAnimator = showAnimator;
        return this;
    }

    Animation getExitAnimation() {
        return mExitAnimation;
    }

    BasePopupHelper setExitAnimation(Animation exitAnimation) {
        if (mExitAnimation == exitAnimation) return this;
        if (mExitAnimation != null) {
            mExitAnimation.cancel();
        }
        mExitAnimation = exitAnimation;
        return this;
    }

    Animator getExitAnimator() {
        return mExitAnimator;
    }

    BasePopupHelper setExitAnimator(Animator exitAnimator) {
        if (mExitAnimator == exitAnimator) return this;
        if (mExitAnimator != null) {
            mExitAnimator.cancel();
        }
        mExitAnimator = exitAnimator;
        return this;
    }

    int getPopupViewWidth() {
        return popupViewWidth;
    }

    BasePopupHelper setPopupViewWidth(int popupViewWidth) {
        this.popupViewWidth = popupViewWidth;
        return this;
    }

    int getPopupViewHeight() {
        return popupViewHeight;
    }

    BasePopupHelper setPopupViewHeight(int popupViewHeight) {
        this.popupViewHeight = popupViewHeight;
        return this;
    }

    boolean isNeedPopupFadeAnima() {
        return needPopupFadeAnima;
    }

    BasePopupHelper setNeedPopupFadeAnima(PopupWindow popupWindow, boolean needPopupFadeAnima) {
        if (popupWindow == null) return this;
        this.needPopupFadeAnima = needPopupFadeAnima;
        popupWindow.setAnimationStyle(needPopupFadeAnima ? R.style.PopupAnimaFade : 0);
        return this;
    }

    boolean isShowAtDown() {
        return showAtDown;
    }

    BasePopupHelper setShowAtDown(boolean showAtDown) {
        this.showAtDown = showAtDown;
        return this;
    }

    int getPopupGravity() {
        return popupGravity;
    }

    BasePopupHelper setPopupGravity(int popupGravity) {
        this.popupGravity = popupGravity;
        return this;
    }

    int getOffsetX() {
        return offsetX;
    }

    BasePopupHelper setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    int getOffsetY() {
        return offsetY;
    }

    BasePopupHelper setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    boolean isAutoShowInputMethod() {
        return autoShowInputMethod;
    }

    BasePopupHelper setAutoShowInputMethod(PopupWindow popupWindow, boolean autoShowInputMethod) {
        if (popupWindow == null) return this;
        this.autoShowInputMethod = autoShowInputMethod;
        popupWindow.setSoftInputMode(autoShowInputMethod ? WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE : WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        return this;
    }

    boolean isAutoLocatePopup() {
        return isAutoLocatePopup;
    }

    BasePopupHelper setAutoLocatePopup(boolean autoLocatePopup) {
        isAutoLocatePopup = autoLocatePopup;
        return this;
    }

    BasePopupWindow.OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    BasePopupHelper setOnDismissListener(BasePopupWindow.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        return this;
    }

    BasePopupWindow.OnBeforeShowCallback getOnBeforeShowCallback() {
        return mOnBeforeShowCallback;
    }

    BasePopupHelper setOnBeforeShowCallback(BasePopupWindow.OnBeforeShowCallback onBeforeShowCallback) {
        mOnBeforeShowCallback = onBeforeShowCallback;
        return this;
    }

    boolean isDismissWhenTouchOutside() {
        return dismissWhenTouchOutside;
    }

    BasePopupHelper setDismissWhenTouchOutside(PopupWindow popupWindow, boolean dismissWhenTouchOutside) {
        if (popupWindow == null) return this;
        this.dismissWhenTouchOutside = dismissWhenTouchOutside;
        if (dismissWhenTouchOutside) {
            //指定透明背景，back键相关
            focusable = true;
            outsideTouchable = true;
        } else {
            focusable = false;
            outsideTouchable = false;
        }
        popupWindow.setFocusable(focusable);
        popupWindow.setOutsideTouchable(outsideTouchable);
        return this;
    }

    boolean isInterceptTouchEvent() {
        return outsideTouchable;
    }

    BasePopupHelper setInterceptTouchEvent(PopupWindow popupWindow, boolean intecept) {
        if (popupWindow == null) return this;
        popupWindow.setFocusable(intecept);
        popupWindow.setOutsideTouchable(!intecept);
        return this;
    }

    BasePopupHelper getAnchorLocation(View v) {
        if (v == null) return this;
        v.getLocationOnScreen(mAnchorViewLocation);
        return this;
    }

    int getAnchorX() {
        return mAnchorViewLocation[0];
    }

    int getAnchorY() {
        return mAnchorViewLocation[1];
    }

    public boolean isBackPressEnable() {
        return backPressEnable;
    }

    BasePopupHelper setBackPressEnable(boolean backPressEnable) {
        this.backPressEnable = backPressEnable;
        return this;
    }
}
