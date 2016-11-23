package com.nightly.lovetravel.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.util.ThreadUtil;

import static android.R.attr.radius;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class AqiView extends TextView {

    private static final String TAG = "test";
    private int select = 0;
    private int b = 0;
    private int g = 0;
    private int r = 255;
    private int alpha = 255;
    private Paint paint;
    private int scale = 360 / 100;
    private boolean fll = true;
    private int i = -1;//实际空气质量aqi
    private String c;
    private float cx = -1;
    private float cy = -1;

    public void setK(int k) {
        this.k = k;
    }

    private int k = -1;////实际空气湿度
    private Handler handler = new Handler();
    private Paint darwRound;


    public AqiView(Context context) {
        this(context, null);
    }

    public AqiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AqiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void startThread() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
                startThread();
            }
        }, 5);
    }

    //实际Aqi数据与原点显示联动
    private void updataAqi(Canvas canvas, float cx, float cy, int radius) {
            String[] s = c.split("-");
            darwRound.setColor(Color.argb(alpha, new Integer(s[0]).intValue()
                    , new Integer(s[1]).intValue(), new Integer(s[2]).intValue()));
            int j;
            if (i < 150) {
                j = (int) (i / 300f * 100) + 70;
            } else {
                j = (int) (i / 300f * 100) - 50;
            }
            Log.e(TAG, "updataAqi: " + j + "/" + i);
            canvas.drawCircle((float) (cx + ((radius - 30 - 15) * Math.sin(Math.toRadians(j * scale))))
                    , (float) (cy - ((radius - 30 - 15) * Math.cos(Math.toRadians(j * scale)))), 5, darwRound);
    }

    public void setI(int i) {
        this.i = i;
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AqiView);
        int i = typedArray.getInteger(R.styleable.AqiView_aqi_view_mode, 0);
        select = i;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(alpha, r, g, b));
        paint.setStrokeWidth(2);
        darwRound = new Paint();
        darwRound.setAntiAlias(true);
        darwRound.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(cx == -1 || cy == -1 || radius == -1) {
            cx = getWidth() / 2f;
            cy = getHeight() / 2f;
        }
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;
        //0：绘画污染指数，1绘画舒适度
        if (select == 0) {
            drawAqi(canvas, cx, cy, radius);
        } else if (select == 1) {
            drawComfortable(canvas, cx, cy, radius);
        }
        if (this.i != -1) {
            updataAqi(canvas, cx, cy, radius);
        }
        r = 255;
        g = 0;
        b = 0;
        fll = true;
    }

    /**
     * 空气湿度的ui
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     */
    private void drawComfortable(Canvas canvas, float cx, float cy, int radius) {
        for (int i = 50; i >= -50; i--) {
            int j = i;
            if (i < 0) {
                j = 120 + i;
            }
            if (k != -1) {
                if (k <= Math.abs(i - 50) + 20) {
                    paint.setColor(Color.argb(alpha, 62, 192, 255));
                } else {
                    paint.setColor(Color.WHITE);
                }
            } else {
                paint.setColor(Color.WHITE);
            }
            draw(canvas, cx, cy, radius, j, 30, 30);

            if (i == 50) {
                paint.setColor(Color.WHITE);
                draw(canvas, cx, cy, radius + 10, j, 40, 40);
            }
            if (i == -50) {
                paint.setColor(Color.WHITE);
                draw(canvas, cx, cy, radius + 10, j, 40, 40);
            }

        }
    }

    /**
     * 空气污染的ui
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     */
    private void drawAqi(Canvas canvas, float cx, float cy, int radius) {
        for (int i = 50; i >= -50; i--) {
            int j = i;
            if (i < 0) {
                j = 120 + i;
            }
            getColor();
            paint.setColor(Color.argb(alpha, r, g, b));
            draw(canvas, cx, cy, radius, j, 30, 30);
            //记录画原点笔的颜色
            if(this.i!=-1) {
                if (j == (int) (this.i / 300f * 100)+70) {
                    c = r + "-" + g + "-" + b;
                }
            }
            if (i == 50) {
                paint.setColor(Color.WHITE);
                draw(canvas, cx, cy, radius + 10, j, 40, 40);
            }
            if (i == -50) {
                paint.setColor(Color.WHITE);
                draw(canvas, cx, cy, radius + 10, j, 40, 40);
            }

        }
    }

    private void getColor() {
        if (r != 0 && fll) {
            g += 10;
            if (g > 255) {
                g = 255;
                fll = false;
            }
        } else if (r != 0 && !fll) {
            r -= 10;
            if (r < 0) {
                r = 0;
                fll = true;
            }
        } else if (fll) {
            b += 10;
            if (b >= 255) {
                b = 255;
                fll = false;
            }
        } else if (!fll) {
            g -= 10;
            if (g < 0) {
                Log.e(TAG, "onDraw: " + "r" + r + "g" + g + "b" + b);
            }
//                Log.e(TAG, "onDraw: "+"r"+r+"g"+g+"b"+b );
        }
    }

    private void draw(Canvas canvas, float cx, float cy, int radius, int j, int begin, int last) {
        canvas.drawLine((float) (cx + radius * Math.sin(Math.toRadians(j * scale)))
                , (float) (cy - (radius * Math.cos(Math.toRadians(j * scale))))
                , (float) ((cx) + (radius - begin) * Math.sin(Math.toRadians(j * scale)))
                , (float) ((cy) - ((radius - last) * Math.cos(Math.toRadians(j * scale))))
                , paint);
    }
}
