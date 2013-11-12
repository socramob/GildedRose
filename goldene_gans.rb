# encoding: utf-8

require './artikel.rb'

class GoldeneGans

  @artikel = []

  attr_reader :artikel

  def initialize
    @artikel = []
    @artikel << Artikel.new("+5 Geschicklichkeits-Weste", 10, 20)
    @artikel << AlterGouda.new("Alter Gouda", 2, 0)
    @artikel << Artikel.new("Mangustenelixir", 5, 7)
    @artikel << Artikel.new("Fedoras", 0, 80)
    @artikel << Artikel.new("Backstage-Pässe für ein Ruby Perry Konzert", 15, 20)
  end


  def aktualisiere_qualitaet

    for i in 0..(@artikel.size-1)
      current_artikel = @artikel[i]
      if(current_artikel.name == "Alter Gouda")
         current_artikel.update_quality
      elsif(current_artikel.name != "Fedoras")
        if (current_artikel.name == "Backstage-Pässe für ein Ruby Perry Konzert")

          if (current_artikel.qualitaet < 50)
            current_artikel.increase_quality(1)
              if (current_artikel.verkaufenIn < 11 && current_artikel.qualitaet < 50)
                current_artikel.increase_quality(1)
              end
              if (current_artikel.verkaufenIn < 6 && current_artikel.qualitaet < 50)
                current_artikel.increase_quality(1)
              end
          end
        else
          if (current_artikel.qualitaet > 0)
            current_artikel.decrease_quality(1)
          end
        end
        current_artikel.verkaufenIn = current_artikel.verkaufenIn - 1;
        if (current_artikel.verkaufenIn < 0)
            if (current_artikel.name != "Backstage-Pässe für ein Ruby Perry Konzert")
              if (current_artikel.qualitaet > 0)
                current_artikel.decrease_quality(1)
              end
            else
              current_artikel.decrease_quality(current_artikel.qualitaet)
            end
          end
      end
    end
  end

end
