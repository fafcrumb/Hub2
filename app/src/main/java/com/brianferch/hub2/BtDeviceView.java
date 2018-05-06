package com.brianferch.hub2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BtDeviceView extends RelativeLayout {

    private Context mContext;
    private TextView deviceName;
    private TextView availableMessage;
    private ProgressBar connectingSpinner;
    private ImageView disconnectButton;

    public BtDeviceView(Context c) {
        super(c);
        init(c);
    }

    public BtDeviceView(Context c, AttributeSet attrs) {
        super(c, attrs);
        init(c);
    }

    public BtDeviceView(Context c, AttributeSet attrs, int defStyle) {
        super(c, attrs, defStyle);
        init(c);
    }

    private void init(Context c) {
        mContext = c;
        View.inflate(c, R.layout.bt_device_layout, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        deviceName = (TextView) findViewById(R.id.bt_device_name);
        availableMessage = (TextView) findViewById(R.id.available_message);
        connectingSpinner = (ProgressBar) findViewById(R.id.connecting_spinner);
        disconnectButton = (ImageView) findViewById(R.id.disconnect_button);
        connectingSpinner.setVisibility(View.GONE);
    }

    @Override
    public void onMeasure(int widthMeasured, int heightMeasured) {
        super.onMeasure(widthMeasured, widthMeasured); // This is the key that will make the height equivalent to its width
    }

    public void setDeviceModel(@NonNull BtDevice deviceModel) {
        this.setDeviceName(deviceModel.getName());

        switch (deviceModel.getConnectionState()) {
            case available:
                this.setBackgroundColor(this.getColor(R.color.colorAvailable));
                this.connectingSpinner.setVisibility(View.GONE);
                this.disconnectButton.setVisibility(View.GONE);
                this.availableMessage.setVisibility(View.VISIBLE);
                break;
            case connecting:
                this.availableMessage.setVisibility(View.GONE);
                this.disconnectButton.setVisibility(View.GONE);
                this.connectingSpinner.setVisibility(View.VISIBLE);
                break;
            case connected:
                this.setBackgroundColor(this.getColor(R.color.colorConnected));
                this.availableMessage.setVisibility(View.GONE);
                this.connectingSpinner.setVisibility(View.GONE);
                this.disconnectButton.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setDeviceName(String deviceName) {
        this.deviceName.setText(deviceName);
    }

    public void animateBackgroundColor(int fromColorResource, int toColorResource) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                this.getColor(fromColorResource), this.getColor(toColorResource));
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    private int getColor(int colorResource) {
        return ContextCompat.getColor(mContext, colorResource);
    }
}
