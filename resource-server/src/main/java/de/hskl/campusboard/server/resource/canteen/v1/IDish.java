/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.canteen.v1;

import java.time.LocalDate;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface IDish
{
    public int getDish_id();

    public void setDish_id(int dish_id);

    public LocalDate getDate();

    public void setDate(LocalDate date);
    
    public String getDescription();

    public void setDescription(String description);

    public int getStars();

    public void setStars(int stars);
    
    public String getCanteen_id();
    
    public void setCanteen_id(String canteen_id);
}
