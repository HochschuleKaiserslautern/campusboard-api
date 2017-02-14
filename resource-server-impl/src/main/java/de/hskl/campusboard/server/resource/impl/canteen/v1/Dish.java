/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.impl.canteen.v1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.hskl.campusboard.server.resource.canteen.v1.IDish;
import java.time.LocalDate;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Entity
@Table(name = Dish.TABLE_NAME)
@NamedQueries(value =
{
    @NamedQuery(name = Dish.GET_BY_CANTEEN, query = "SELECT d FROM Dish d WHERE d.canteen_id = :canteenId")
})
public class Dish implements IDish
{
    protected static final String TABLE_NAME = "dish";
    private static final String QUERY_PREFIX = "DISH_QUERY_";

    public static final String GET_BY_CANTEEN = QUERY_PREFIX + "GET_BY_CANTEEN";

    @Id
    @Column(name = "id")
    private int dish_id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "description")
    private String description;
    @Column(name = "rating")
    private int stars;    
    @Column(name="canteen_id")
    private String canteen_id;

    @Override
    public LocalDate getDate()
    {
        return date;
    }

    @Override
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int getStars()
    {
        return stars;
    }

    @Override
    public void setStars(int stars)
    {
        this.stars = stars;
    }

    @Override
    public int getDish_id()
    {
        return dish_id;
    }

    @Override
    public void setDish_id(int dish_id)
    {
        this.dish_id = dish_id;
    }

    @Override
    public String getCanteen_id()
    {
        return canteen_id;
    }

    @Override
    public void setCanteen_id(String canteen_id)
    {
        this.canteen_id = canteen_id;
    }
}
