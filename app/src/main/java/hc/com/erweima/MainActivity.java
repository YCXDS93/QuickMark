package hc.com.erweima;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView tv_content;
    private EditText et_input;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_content = (TextView) findViewById(R.id.tv_content);
        et_input = (EditText) findViewById(R.id.et_input);
        img = (ImageView) findViewById(R.id.img);
        findViewById(R.id.btnSan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
            }
        });

        findViewById(R.id.btn_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et_input.getText().toString();
                if (str.equals("")) {
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 位图
                    try {
                        /**
                         * 参数：1.文本 2 3.二维码的宽高 4.二维码中间的那个logo
                         */
                        Bitmap bitmap = EncodingUtils.createQRCode(str, 500, 500, null);
                        // 设置图片
                        img.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            tv_content.setText(result);
        }
    }
}
