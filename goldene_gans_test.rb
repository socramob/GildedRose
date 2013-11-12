# encoding: utf-8

require './goldene_gans.rb'
require './artikel.rb'
require 'minitest/autorun'
begin
  require 'minitest/reporters'
  MiniTest::Reporters.use!
rescue LoadError
  puts "minitest/reporters is not installed.\nYou can do so by executing \`gem install minitest-reporters\`\n\n"
end

describe GoldeneGans do
  
  subject do
    GoldeneGans.new
  end

  describe 'Fedoras' do
    it "veraendert seine Qualitaet nicht" do
      # Arrange
      fedoras = subject.artikel[3]

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      fedoras.qualitaet.must_equal 80
    end
  end

  describe 'Default' do
    it "Qualität sinkt um 1" do
      # Arrange
      defaultArtikel = subject.artikel[0]

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      defaultArtikel.qualitaet.must_equal 19
    end

    it "Qualität sinkt um 2" do
      # Arrange
      defaultArtikel = subject.artikel[0]
      defaultArtikel.verkaufenIn = -1

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      defaultArtikel.qualitaet.must_equal 18
    end

    it "VerkaufenIn sinkt um 1" do
      # Arrange
      defaultArtikel = subject.artikel[0]

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      defaultArtikel.verkaufenIn.must_equal 9
    end

    it "Qualitaet sinkt nicht unter 0" do
      # Arrange
      defaultArtikel = subject.artikel[0]
      defaultArtikel.qualitaet = 0

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      defaultArtikel.qualitaet.must_equal 0
    end
  end

  describe 'Alter Gouda' do
    it "steigt in der Qualitaet um 1" do
      #Arrange
      alterGouda = subject.artikel[1]

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      alterGouda.qualitaet.must_equal 1
    end

    it "übersteigt 50 nicht" do
      #Arrange
      alterGouda = subject.artikel[1]
      alterGouda.qualitaet = 50

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      alterGouda.qualitaet.must_equal 50
    end

    it "steigt um 2, wenn verkaufenIn < 0 ist" do
      #Arrange
      alterGouda = subject.artikel[1]
      alterGouda.qualitaet = 0
      alterGouda.verkaufenIn = -1

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      alterGouda.qualitaet.must_equal 2
    end

    it "uebersteigt 50 nicht, wenn verkaufenIn < 0 ist" do
      #Arrange
      alterGouda = subject.artikel[1]
      alterGouda.qualitaet = 49
      alterGouda.verkaufenIn = -1

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      alterGouda.qualitaet.must_equal 50
    end
  end

  describe 'Backstage Pässe' do
    it "Qualität steigt um 1" do
      #Arrange
      paesse = subject.artikel[4]

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 21
    end

    it "übersteigt 50 nicht in den letzten 10 Tagen" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.qualitaet = 49
      paesse.verkaufenIn = 10

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      paesse.qualitaet.must_equal 50
    end

    it "übersteigt 50 nicht in den letzten 5 Tagen" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.qualitaet = 49
      paesse.verkaufenIn = 5

      # Act
      subject.aktualisiere_qualitaet

      # Assert
      paesse.qualitaet.must_equal 50
    end

    it "Qualität steigt um 2" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.verkaufenIn = 10
      paesse.qualitaet = 20

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 22
    end

    it "Qualität steigt um 3" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.verkaufenIn = 5
      paesse.qualitaet = 20

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 23
    end

    it "Qualität steigt um 1, wenn verkaufen in 11" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.verkaufenIn = 11
      paesse.qualitaet = 20

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 21
    end

    it "Qualität steigt um 2, wenn verkaufen in 6" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.verkaufenIn = 6
      paesse.qualitaet = 20

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 22
    end

    it "Qualität wird auf 0 gesetzt" do
      #Arrange
      paesse = subject.artikel[4]
      paesse.verkaufenIn = -1
      paesse.qualitaet = 20

      #Act
      subject.aktualisiere_qualitaet

      #Assert
      paesse.qualitaet.must_equal 0
    end
  end



end
