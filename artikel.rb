# encoding: utf-8

class Artikel

  attr_accessor :name, :verkaufenIn, :qualitaet

  def initialize (name, verkaufenIn, qualitaet)
    @name = name
    @verkaufenIn = verkaufenIn
    @qualitaet = qualitaet
    @maxQuality = 50
    @minQuality = 0
  end

  def increase_quality(steps)
    @qualitaet = @qualitaet + steps
    if(@qualitaet > @maxQuality)
      @qualitaet = @maxQuality
    end
  end


  def decrease_quality (steps)
    @qualitaet -= steps
    if(@qualitaet < @minQuality)
      @qualitaet = @minQuality
    end
  end

  def update_quality()
    @verkaufenIn -= 1
    if(@verkaufenIn < 0)
      decrease_quality 2
    else
      decrease_quality 1
    end
  end
end

class AlterGouda < Artikel

  def update_quality()
    @verkaufenIn -= 1
    if (@verkaufenIn < 0)
      increase_quality 2
    else
      increase_quality 1
    end
  end
end

class Backstagepaesse < Artikel

  def update_quality()
    @verkaufenIn -= 1
    if (@verkaufenIn < 0)
      @qualitaet = 0
    elsif(@verkaufenIn < 11)
      increase_quality 2
    elsif (@verkaufenIn < 6)
      increase_quality 3
    else
      increase_quality 1
    end
  end

end
