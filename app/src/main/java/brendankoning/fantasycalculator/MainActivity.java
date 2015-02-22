package brendankoning.fantasycalculator;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView kills, assists, deaths, creep_score, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                calculate();
                return true;
            }
        };



        this.findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });



        kills = (TextView) findViewById(R.id.killsInput);
        assists = (TextView) findViewById(R.id.assistsInput);
        deaths = (TextView) findViewById(R.id.deathsInput);
        points = (TextView) findViewById(R.id.points);
        creep_score = (TextView) findViewById(R.id.csInput);
        creep_score.setOnEditorActionListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void calculate(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(new View(this).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        double killCount, assistsCount, deathCount, creepCount, pointCount;
        String totalPoints;

        killCount = Double.parseDouble(kills.getText().toString());
        assistsCount = Double.parseDouble(assists.getText().toString());
        deathCount = Double.parseDouble(deaths.getText().toString());
        creepCount = Double.parseDouble(creep_score.getText().toString());

        pointCount = (killCount * 2) + (assistsCount * 1.5) - (deathCount*0.5) + (creepCount*0.01);

        pointCount = Math.round(pointCount * 100);
        pointCount = pointCount/100;

        totalPoints = String.valueOf(pointCount);

        points.setText(totalPoints);
    }


}
