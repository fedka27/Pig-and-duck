package app.meat.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import app.meat.util.NotificationUtil;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected NotificationUtil notificationUtil;
    private FragmentManager fragmentManager;
    private Fragment progressFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        notificationUtil = new NotificationUtil(getContext(), view);
    }

    protected void replaceFragment(@IdRes int containerId,
                                   FragmentManager fragmentManager,
                                   BaseFragment fragment,
                                   boolean addToBackStack) {
        showFragment(containerId, fragmentManager, fragment, addToBackStack);
    }

    protected void replaceFragment(@IdRes int containerId,
                                   BaseFragment fragment,
                                   boolean addToBackStack) {
        showFragment(containerId, fragmentManager, fragment, addToBackStack);
    }


    protected void replaceFragment(@IdRes int containerId,
                                   FragmentManager fragmentManager,
                                   BaseFragment fragment) {
        showFragment(containerId, fragmentManager, fragment, false);
    }

    private void showFragment(@IdRes int containerId,
                              FragmentManager fragmentManager,
                              BaseFragment fragment,
                              boolean addToBackStack) {
        try {
            ((FrameLayout) getActivity().findViewById(containerId)).removeAllViews();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
            this.fragmentManager.executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBaseProgress() {
        if (progressFragment != null && progressFragment.isVisible()) {
            return;
        }
        progressFragment = fragmentManager.findFragmentByTag("progress");
        try {
            if (progressFragment == null) {
                progressFragment = new ProgressFragment();
            }
            if (progressFragment.isAdded()) {
                hideBaseProgress();
            }
            fragmentManager.beginTransaction()
                    .add(getId(), progressFragment, "progress")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
            hideBaseProgress();
        }

    }

    public void hideBaseProgress() {
        if (progressFragment != null) {
            fragmentManager.beginTransaction()
                    .remove(progressFragment)
                    .commitAllowingStateLoss();
        }
    }

    protected void setBackground(@DrawableRes int background) {
        getActivity().getWindow().setBackgroundDrawableResource(background);
    }

    @Override
    public void onPause() {
        super.onPause();
        hideBaseProgress();
    }
}
