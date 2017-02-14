/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.canteen.v1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.hskl.campusboard.server.resource.canteen.v1.ICanteen;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Entity
@Table(name=Canteen.TABLE_NAME)
@NamedQueries(value =
{ @NamedQuery(name=Canteen.GET_ALL, query="SELECT c FROM Canteen c") })
public class Canteen implements ICanteen
{
    protected static final String TABLE_NAME = "canteen";
    private static final String QUERY_PREFIX = "CANTEEN_QUERY_";
    
    public static final String GET_ALL = QUERY_PREFIX + "GET_ALL";
    
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    
    @Override
    public String getId()
    {
        return id;
    }
    
    @Override
    public void setId(String id)
    {
        this.id = id;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
}
