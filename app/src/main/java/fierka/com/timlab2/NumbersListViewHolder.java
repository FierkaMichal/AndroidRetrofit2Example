package fierka.com.timlab2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Michał on 2018-06-20.
 */

public class NumbersListViewHolder extends RecyclerView.ViewHolder {

    private TextView numberTextView;

    public NumbersListViewHolder(View itemView) {
        super(itemView);
        numberTextView = itemView.findViewById(R.id.list_item_number);
    }

    public void bindNumber(Integer number) {
        numberTextView.setText(String.valueOf(number));
    }
}
