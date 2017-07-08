package app.meat.view.widget.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.TextView;

import app.meat.R;
import app.meat.view.base.BaseCustomView;
import butterknife.BindView;


public class TitleToolbar extends BaseCustomView {
    @BindView(R.id.title_toolbar_text_view)
    TextView titleToolbarTextView;

    public TitleToolbar(@NonNull Context context) {
        super(context);
    }

    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int layout() {
        return R.layout.view_toolbar_title;
    }

    @Override
    protected void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet,
                R.styleable.BaseCustomView, 0, 0);
        CharSequence title = typedArray.getText(R.styleable.BaseCustomView_toolbar_title);
        setTitle(title);
        typedArray.recycle();
    }

    private void setTitle(CharSequence title) {
        titleToolbarTextView.setText(title);
    }

    public void setTitle(String title) {
        titleToolbarTextView.setText(title);
    }
}
