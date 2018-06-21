package fierka.com.timlab2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 2018-06-20.
 */

public class NumbersListAdapter extends RecyclerView.Adapter<NumbersListViewHolder> {

    private List<Integer> numbers = new ArrayList<>();

    public NumbersListAdapter(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public NumbersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.numbers_list_item, parent, false);
        return new NumbersListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumbersListViewHolder holder, int position) {
        Integer number = numbers.get(position);
        holder.bindNumber(number);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
