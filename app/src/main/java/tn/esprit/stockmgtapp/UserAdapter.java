package tn.esprit.stockmgtapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        }

        // Get the current user
        final User currentUser = userList.get(position);

        // Set user details to the TextViews
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView userEmail = convertView.findViewById(R.id.user_email);
        userName.setText(currentUser.getUsername());
        userEmail.setText(currentUser.getEmail());

        // Set up buttons for edit and delete actions
        Button btnEdit = convertView.findViewById(R.id.btn_edit_user);
        Button btnDelete = convertView.findViewById(R.id.btn_delete_user);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle user edit
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle user delete
            }
        });

        return convertView;
    }
}
