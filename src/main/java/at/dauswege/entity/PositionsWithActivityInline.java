package at.dauswege.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "positionsWithActivityInline", types = {Position.class})
public interface PositionsWithActivityInline {

  String getActivity();

}
