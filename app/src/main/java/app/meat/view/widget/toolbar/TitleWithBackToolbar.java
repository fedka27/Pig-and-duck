package app.meat.view.widget.toolbar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import app.meat.R;
import butterknife.BindView;


public class TitleWithBackToolbar extends TitleToolbar {
    @BindView(R.id.title_toolbar_text_view)
    TextView titleToolbarTextView;
    @BindView(R.id.back_navigation_button)
    ImageButton backNavigationButton;

    public TitleWithBackToolbar(@NonNull Context context) {
        super(context);
    }

    public TitleWithBackToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleWithBackToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleWithBackToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int layout() {
        return R.layout.view_toolbar_title_with_back;
    }


    public void setOnBackClickListener(View.OnClickListener onClickListener) {
        backNavigationButton.setOnClickListener(onClickListener);
    }

    public void setTitle(String title) {
        titleToolbarTextView.setText(title);
    }
}
