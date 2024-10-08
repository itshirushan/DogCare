package com.example.dogcare;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SeeProducts extends AppCompatActivity {

    private Cursor mycursor;
    private SQLiteDatabase db;
    private AddFoodDBOpenHelper dbhelp;
    private EditText txtFoodID;
    private EditText txtS1;
    private EditText txtS2;
    private EditText txtS3;
    private int len;
    private int currentRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_products);

        txtFoodID = findViewById(R.id.foodid);
        txtS1 = findViewById(R.id.s1);
        txtS2 = findViewById(R.id.s2);
        txtS3 = findViewById(R.id.s3);

        dbhelp = new AddFoodDBOpenHelper(this);
        db = dbhelp.getWritableDatabase();

        readdata();
        if (mycursor != null && mycursor.moveToFirst()) {
            currentRec = 1;
            setdata();
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void readdata() {
        if (db != null) {
            mycursor = db.query("foods", new String[]{"foodid", "s1", "s2", "s3"}, null, null, null, null, null);
            len = mycursor != null ? mycursor.getCount() : 0;
        } else {
            Toast.makeText(this, "Database not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void setdata() {
        if (mycursor != null && mycursor.moveToPosition(currentRec - 1)) {
            txtFoodID.setText(mycursor.getString(0)); // Column 0: foodid
            txtS1.setText(mycursor.getString(1)); // Column 1: s1
            txtS2.setText(mycursor.getString(2)); // Column 2: s2
            txtS3.setText(mycursor.getString(3)); // Column 3: s3
        }
    }

    public void first(View v) {
        if (mycursor != null && len > 0) {
            mycursor.moveToFirst();
            currentRec = 1;
            setdata();
        }
    }

    public void next(View v) {
        if (mycursor != null && currentRec < len) {
            mycursor.moveToNext();
            currentRec++;
            setdata();
        } else {
            Toast.makeText(this, "This is the last record", Toast.LENGTH_SHORT).show();
        }
    }

    public void previous(View v) {
        if (mycursor != null && currentRec > 1) {
            mycursor.moveToPrevious();
            currentRec--;
            setdata();
        } else {
            Toast.makeText(this, "This is the first record", Toast.LENGTH_SHORT).show();
        }
    }

    public void last(View v) {
        if (mycursor != null && len > 0) {
            mycursor.moveToLast();
            currentRec = len;
            setdata();
        }
    }

    public void update(View v) {
        try {
            String foodID = txtFoodID.getText().toString();
            String s1 = txtS1.getText().toString();
            String s2 = txtS2.getText().toString();
            String s3Str = txtS3.getText().toString();

            // Validate s3 to ensure it is an integer
            if (!s3Str.matches("\\d+")) {
                Toast.makeText(this, "Please enter a valid integer for s3", Toast.LENGTH_SHORT).show();
                return;
            }

            int s3 = Integer.parseInt(s3Str);

            if (!foodID.isEmpty() && !s1.isEmpty() && !s2.isEmpty()) {
                dbhelp.updateFoodDetails(db, s1, s2, String.valueOf(s3), foodID);  // Update s1, s2, and s3
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
                readdata();
                if (mycursor != null && mycursor.moveToFirst()) {
                    currentRec = 1;
                    setdata();
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Update failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Record");
        builder.setMessage("Are you sure you want to delete this record?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String foodID = txtFoodID.getText().toString();
                    if (!foodID.isEmpty()) {
                        dbhelp.deleteFood(db, foodID);
                        Toast.makeText(SeeProducts.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                        readdata();
                        if (mycursor != null && mycursor.moveToFirst()) {
                            currentRec = 1;
                            setdata();
                        }
                    } else {
                        Toast.makeText(SeeProducts.this, "No record selected", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SeeProducts.this, "Delete failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mycursor != null) {
            mycursor.close();
        }
        if (db != null) {
            db.close();
        }
    }
}
