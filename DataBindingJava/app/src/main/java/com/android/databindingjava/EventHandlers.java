package com.android.databindingjava;

import android.view.View;

public interface EventHandlers {
    // クリックイベントに対応させたいため、引数はView.OnClickListenerのonClickと同じ（View view）にする
    void onChangeClick(View view);
}
