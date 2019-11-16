package sample;

import sample.model.Grain;
import sample.types.Boundary;
import sample.types.Neighboorhood;

import java.util.Random;

public class Algorithms {


    public Neighboorhood neighboorhood;
    public Boundary boundary;
    public Grain grains[][];
    public int count;
    private Grain[][] temp;

    public Algorithms(Neighboorhood neighboorhood, Boundary boundary, Grain[][] grains) {
        this.neighboorhood = neighboorhood;
        this.boundary = boundary;
        this.grains = grains;
        count = grains.length;
    }

    public Algorithms(Neighboorhood neighboorhood, Boundary boundary) {
        this.neighboorhood = neighboorhood;
        this.boundary = boundary;
    }

    public void getAlgorithmEffect(int count) {

        temp = new Grain[count][count];

        for (int i=0; i<count; i++) {
            for (int j = 0; j < count; j++) {
                    temp[i][j] = grains[i][j];

                switch (neighboorhood) {
                    case VonNeumann: {
//                        VonNeumanMethod();
                        break;
                    }
                    case Moorea: {
//                        MooreaMethod();
                        break;
                    }
                    case HexagonalLeft: {
//                        HexagonalLeftMethod();
                        break;
                    }

                    case HexagonalRight: {
//                        HexagonalRightMethod();
                        break;
                    }

                    case HexagonalRandom: {
//                        HexagonalRandomMethod();
                        break;
                    }

                    case PentagonalLeft: {
//                        PentagonalLeftMethod();
                        break;
                    }

                    case PentagonalRight: {
//                        PentagonalRightMethod();
                        break;
                    }

                    case PentagonalRandom: {
//                        PentagonalRandomMethod();
                        break;
                    }
                }
            }
        }
    }

    private void PentagonalRandomMethod(int i, int j) {

        Random random = new Random();
        int path = random.nextInt(4);

        switch (path) {
            case 0:
                //j+1 wywalone
                if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                    if (boundary == Boundary.Periodic) {
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[x-1][j+1].isEmpty()) fields[x-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][count - 1].isGrainColor())
                                    grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][count - 1].isGrainColor())
                                    grains[count - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][0].isEmpty()) fields[i][0].changeColor(fields[i][j].getColor());
                                //if(old[x-1][0].isEmpty()) fields[x-1][0].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j - 1].isGrainColor())
                                    grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][0].isEmpty()) fields[i+1][0].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[x-1][j+1].isEmpty()) fields[x-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j - 1].isGrainColor())
                                    grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[0][count - 1].isGrainColor())
                                    grains[0][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][count - 1].isGrainColor())
                                    grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j+1].isEmpty()) fields[0][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][0].isEmpty()) fields[i][0].changeColor(fields[i][j].getColor());
                                //if(old[i-1][0].isEmpty()) fields[i-1][0].changeColor(fields[i][j].getColor());
                                if (temp[0][j - 1].isGrainColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][0].isEmpty()) fields[0][0].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[0][j - 1].isGrainColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j+1].isEmpty()) fields[0][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][count - 1].isGrainColor())
                                grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][count - 1].isGrainColor())
                                grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i][0].isEmpty()) fields[i][0].changeColor(fields[i][j].getColor());
                            //if(old[i-1][0].isEmpty()) fields[i-1][0].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][0].isEmpty()) fields[i+1][0].changeColor(fields[i][j].getColor());
                        }


                    } else {//nieperiodyczne
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }


                        }

                    }
                } else {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                    //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                    if (temp[i + 1][j - 1].isGrainColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j - 1].isGrainColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                }

                break;
            case 1:
                //j-1 wywalić
                if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                    if (boundary == Boundary.Periodic) {
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][y-1].isEmpty()) fields[i][y-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j + 1].isGrainColor())
                                    grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][y-1].isEmpty()) fields[i+1][y-1].changeColor(fields[i][j].getColor());
                                //if(old[x-1][y-1].isEmpty()) fields[x-1][y-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][0].isGrainColor())
                                    grains[count - 1][0].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[x-1][j-1].isEmpty()) fields[x-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][0].isGrainColor())
                                    grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                            } else {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j + 1].isGrainColor())
                                    grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[x-1][j-1].isEmpty()) fields[x-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][y-1].isEmpty()) fields[i][y-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][y-1].isEmpty()) fields[0][y-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][y-1].isEmpty()) fields[i-1][y-1].changeColor(fields[i][j].getColor());
                                if (temp[0][j + 1].isGrainColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][0].isGrainColor())
                                    grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j-1].isEmpty()) fields[0][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[0][0].isGrainColor())
                                    grains[0][0].changeGrainColor(grains[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j-1].isEmpty()) fields[0][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[0][j + 1].isGrainColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            //if(old[i][y-1].isEmpty()) fields[i][y-1].changeColor(fields[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j + 1].isGrainColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            // if(old[i+1][y-1].isEmpty()) fields[i+1][y-1].changeColor(fields[i][j].getColor());
                            //if(old[i-1][y-1].isEmpty()) fields[i-1][y-1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][0].isGrainColor())
                                grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                            //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][0].isGrainColor())
                                grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                        }


                    } else {//nieperiodyczne
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                // if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                // if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                // if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                // if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }


                        }

                    }
                } else {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    //if(old[i][j-1].isEmpty()) fields[i][j-1].changeColor(fields[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j + 1].isGrainColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                    //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                    if (temp[i + 1][j + 1].isGrainColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                }

                break;
            case 2:
                //i-1 wywalić
                if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                    if (boundary == Boundary.Periodic) {
                        if (i == 0) {
                            if (j == 0) {
                                //if(old[x-1][j].isEmpty()) fields[x-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][j+1].isEmpty()) fields[x-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][count - 1].isGrainColor())
                                    grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][y-1].isEmpty()) fields[x-1][y-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                //if(old[x-1][j].isEmpty()) fields[x-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][0].isEmpty()) fields[x-1][0].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][j-1].isEmpty()) fields[x-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][0].isGrainColor())
                                    grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                            } else {
                                //if(old[x-1][j].isEmpty()) fields[x-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][j+1].isEmpty()) fields[x-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[x-1][j-1].isEmpty()) fields[x-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {
                                // if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[0][count - 1].isGrainColor())
                                    grains[0][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][y-1].isEmpty()) fields[i-1][y-1].changeColor(fields[i][j].getColor());
                                if (temp[0][j + 1].isGrainColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][0].isEmpty()) fields[i-1][0].changeColor(fields[i][j].getColor());
                                if (temp[0][j - 1].isGrainColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[0][0].isGrainColor())
                                    grains[0][0].changeGrainColor(grains[i][j].getColor());
                            } else {
                                //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[0][j].isGrainColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[0][j - 1].isGrainColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[0][j + 1].isGrainColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (j == 0) {
                            //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][count - 1].isGrainColor())
                                grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][y-1].isEmpty()) fields[i-1][y-1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][0].isEmpty()) fields[i-1][0].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][0].isGrainColor())
                                grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                        }


                    } else {//nieperiodyczne
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i + 1][j].isGrainColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j - 1].isGrainColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i + 1][j + 1].isGrainColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {

                                //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {

                                //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }


                        }

                    }
                } else {
                    //if(old[i-1][j].isEmpty()) fields[i-1][j].changeColor(fields[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                    if (temp[i + 1][j - 1].isGrainColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                    if (temp[i + 1][j + 1].isGrainColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                }

                break;
            case 3:
                //i+1 wywalić
                if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                    if (boundary == Boundary.Periodic) {
                        if (i == 0) {
                            if (j == 0) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j + 1].isGrainColor())
                                    grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][y-1].isEmpty()) fields[i+1][y-1].changeColor(fields[i][j].getColor());
                                if (temp[count - 1][count - 1].isGrainColor())
                                    grains[count - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][0].isGrainColor())
                                    grains[count - 1][0].changeGrainColor(grains[i][j].getColor());
                                // if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[count - 1][j - 1].isGrainColor())
                                    grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][0].isEmpty()) fields[i+1][0].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[count - 1][j].isGrainColor())
                                    grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[count - 1][j + 1].isGrainColor())
                                    grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[count - 1][j - 1].isGrainColor())
                                    grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j].isEmpty()) fields[0][j].changeColor(fields[i][j].getColor());
                                if (temp[i][count - 1].isGrainColor())
                                    grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][y-1].isEmpty()) fields[0][y-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][count - 1].isGrainColor())
                                    grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                                // if(old[0][j+1].isEmpty()) fields[0][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j].isEmpty()) fields[0][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][0].isGrainColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][0].isGrainColor())
                                    grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j-1].isEmpty()) fields[0][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][0].isEmpty()) fields[0][0].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j].isEmpty()) fields[0][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j-1].isEmpty()) fields[0][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[0][j+1].isEmpty()) fields[0][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j + 1].isGrainColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][y-1].isEmpty()) fields[i+1][y-1].changeColor(fields[i][j].getColor());
                            if (temp[i - 1][count - 1].isGrainColor())
                                grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][0].isGrainColor())
                                grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][0].isEmpty()) fields[i+1][0].changeColor(fields[i][j].getColor());
                        }


                    } else {//nieperiodyczne
                        if (i == 0) {
                            if (j == 0) {
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                // if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                // if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }

                        } else if (i == count - 1) {
                            if (j == 0) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else if (j == count - 1) {

                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            } else {
                                if (temp[i - 1][j].isGrainColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j - 1].isGrainColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i][j + 1].isGrainColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                                if (temp[i - 1][j + 1].isGrainColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                                if (temp[i - 1][j - 1].isGrainColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                                //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                            }


                        }

                    }
                } else {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    //if(old[i+1][j].isEmpty()) fields[i+1][j].changeColor(fields[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j + 1].isGrainColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                    if (temp[i - 1][j - 1].isGrainColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                }


                break;
        }


    }

    private void PentagonalRightMethod() {
    }

    private void MooreaMethod(int i, int j) {

        if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
            if (boundary==Boundary.Periodic) {
                if (i == 0) {
                    if (j == 0) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j + 1].isGrainColor())
                            grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][count - 1].isGrainColor())
                            grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][count - 1].isGrainColor())
                            grains[count - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][0].isGrainColor())
                            grains[count - 1][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j - 1].isGrainColor())
                            grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][0].isGrainColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j + 1].isGrainColor())
                            grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j - 1].isGrainColor())
                            grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][count - 1].isGrainColor())
                            grains[0][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][count - 1].isGrainColor())
                            grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j + 1].isGrainColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][0].isGrainColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j - 1].isGrainColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][0].isGrainColor())
                            grains[0][0].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j - 1].isGrainColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j + 1].isGrainColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (j == 0) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][count - 1].isGrainColor())
                        grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j + 1].isGrainColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][count - 1].isGrainColor())
                        grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][count - 1].isGrainColor())
                        grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j + 1].isGrainColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                } else if (j == count - 1) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][0].isGrainColor()) grains[i][0].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][0].isGrainColor())
                        grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j - 1].isGrainColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j - 1].isGrainColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][0].isGrainColor())
                        grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                }


            } else {//nieperiodyczne
                if (i == 0) {
                    if (j == 0) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                        //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                    } else {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                        //if(old[i-1][j-1].isEmpty()) fields[i-1][j-1].changeColor(fields[i][j].getColor());
                        //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                    } else if (j == count - 1) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j+1].isEmpty()) fields[i+1][j+1].changeColor(fields[i][j].getColor());
                    }


                }

            }
        } else {
            if (temp[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i - 1][j + 1].isGrainColor())
                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j - 1].isGrainColor())
                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i - 1][j - 1].isGrainColor())
                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j + 1].isGrainColor())
                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
        }


    }

    private void VonNeumanMethod(int i, int j) {

        if (i == 0 || i == count - 1 || j == 0 || j== count - 1) {
            if (boundary==Boundary.Periodic) {
                if (i == 0) {
                    if (j == 0) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][01].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    }
                    } else if (i == count - 1) {
                        if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                    }
                } else {
                if (i == 0) {
                    if (j == 0) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    }


                }

            }
            } else {
            if (temp[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
        }
        }




    private void  HexagonalLeftMethod(int i, int j) {
        if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
            if (boundary == Boundary.Periodic)
             {
                if (i == 0) {
                    if (j == 0) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][count - 1].isGrainColor())
                            grains[count - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j - 1].isGrainColor())
                            grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][0].isGrainColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j - 1].isGrainColor())
                            grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][count - 1].isGrainColor())
                            grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j + 1].isGrainColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][0].isGrainColor())
                            grains[0][0].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j + 1].isGrainColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (j == 0) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][count - 1].isGrainColor())
                        grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][count - 1].isGrainColor())
                        grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j + 1].isGrainColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                } else if (j == count - 1) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][0].isGrainColor()) grains[i][0].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j - 1].isGrainColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][0].isGrainColor())
                        grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                }


            } else {//nieperiodyczne
                if (i == 0) {
                    if (j == 0) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());

                    } else {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());

                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());

                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                    }


                }

            }
        } else {
            if (temp[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i - 1][j - 1].isGrainColor())
                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j + 1].isGrainColor())
                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
        }

    }

    private void PentagonalLeftMethod() {

    }

    private void HexagonalRandomMethod(int i, int j) {

        Random random = new Random();
        int path = random.nextInt(2);

        if (path == 0) {

            if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                if (boundary == Boundary.Periodic) {
                    if (i == 0) {
                        if (j == 0) {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][j + 1].isGrainColor())
                                grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][count - 1].isGrainColor())
                                grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][01].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][0].isGrainColor())
                                grains[count - 1][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][j + 1].isGrainColor())
                                grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (i == count - 1) {
                        if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j + 1].isGrainColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][count - 1].isGrainColor())
                                grains[0][count - 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][0].isGrainColor())
                                grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j - 1].isGrainColor())
                                grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j + 1].isGrainColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j - 1].isGrainColor())
                                grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][count - 1].isGrainColor())
                            grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][0].isGrainColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    }


                } else {
                    if (i == 0) {
                        if (j == 0) {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (i == count - 1) {
                        if (j == 0) {

                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            if (temp[i + 1][j - 1].isGrainColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {

                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                            //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                        } else {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j + 1].isGrainColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                        }


                    }

                }
            } else {
                if (temp[i - 1][j].isGrainColor())
                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                if (temp[i + 1][j].isGrainColor())
                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                if (temp[i][j - 1].isGrainColor())
                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i][j + 1].isGrainColor())
                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i - 1][j + 1].isGrainColor())
                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i + 1][j - 1].isGrainColor())
                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
            }

        } else {
            if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
                if (boundary == Boundary.Periodic) {
                    if (i == 0) {
                        if (j == 0) {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][count - 1].isGrainColor())
                                grains[count - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][j - 1].isGrainColor())
                                grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][0].isGrainColor())
                                grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[count - 1][j].isGrainColor())
                                grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[count - 1][j - 1].isGrainColor())
                                grains[count - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (i == count - 1) {
                        if (j == 0) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][count - 1].isGrainColor())
                                grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][count - 1].isGrainColor())
                                grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j + 1].isGrainColor())
                                grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][0].isGrainColor())
                                grains[i][0].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][0].isGrainColor())
                                grains[0][0].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j].isGrainColor())
                                grains[0][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[0][j + 1].isGrainColor())
                                grains[0][j + 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][count - 1].isGrainColor())
                            grains[i - 1][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j + 1].isGrainColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j - 1].isGrainColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][0].isGrainColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getColor());
                    }


                } else {//nieperiodyczne
                    if (i == 0) {
                        if (j == 0) {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());

                        } else {
                            if (temp[i + 1][j].isGrainColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i + 1][j + 1].isGrainColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        }

                    } else if (i == count - 1) {
                        if (j == 0) {

                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        } else if (j == count - 1) {

                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());

                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                        } else {
                            if (temp[i - 1][j].isGrainColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j - 1].isGrainColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                            if (temp[i][j + 1].isGrainColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getColor());

                            if (temp[i - 1][j - 1].isGrainColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                            //if(old[i+1][j+1].isEmpty()) fields[i][j+1].changeColor(fields[i][j].getColor());
                        }


                    }

                }
            } else {
                if (temp[i - 1][j].isGrainColor())
                    grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                if (temp[i + 1][j].isGrainColor())
                    grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                if (temp[i][j - 1].isGrainColor())
                    grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i][j + 1].isGrainColor())
                    grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i - 1][j - 1].isGrainColor())
                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getColor());
                if (temp[i + 1][j + 1].isGrainColor())
                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getColor());
            }


        }

    }

    private void HexagonalRightMethod(int i, int j) {

        if (i == 0 || i == count - 1 || j == 0 || j == count - 1) {
            if (boundary == Boundary.Periodic) {
                if (i == 0) {
                    if (j == 0) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j + 1].isGrainColor())
                            grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][count - 1].isGrainColor())
                            grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][01].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][0].isGrainColor())
                            grains[count - 1][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[count - 1][j].isGrainColor())
                            grains[count - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[count - 1][j + 1].isGrainColor())
                            grains[count - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][count - 1].isGrainColor())
                            grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][count - 1].isGrainColor())
                            grains[0][count - 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][0].isGrainColor())
                            grains[i][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][0].isGrainColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j - 1].isGrainColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j].isGrainColor())
                            grains[0][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[0][j - 1].isGrainColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (j == 0) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][count - 1].isGrainColor())
                        grains[i][count - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j + 1].isGrainColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][j + 1].isGrainColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][count - 1].isGrainColor())
                        grains[i + 1][count - 1].changeGrainColor(grains[i][j].getColor());
                } else if (j == count - 1) {
                    if (temp[i - 1][j].isGrainColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j].isGrainColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][j - 1].isGrainColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                    if (temp[i][0].isGrainColor()) grains[i][0].changeGrainColor(grains[i][j].getColor());
                    if (temp[i - 1][0].isGrainColor())
                        grains[i - 1][0].changeGrainColor(grains[i][j].getColor());
                    if (temp[i + 1][j - 1].isGrainColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                }


            } else {//nieperiodyczne
                if (i == 0) {
                    if (j == 0) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else {
                        if (temp[i + 1][j].isGrainColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    }

                } else if (i == count - 1) {
                    if (j == 0) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i-1][j+1].isEmpty()) fields[i-1][j+1].changeColor(fields[i][j].getColor());
                        if (temp[i + 1][j - 1].isGrainColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
                    } else if (j == count - 1) {

                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                    } else {
                        if (temp[i - 1][j].isGrainColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j - 1].isGrainColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i][j + 1].isGrainColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
                        if (temp[i - 1][j + 1].isGrainColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
                        //if(old[i+1][j-1].isEmpty()) fields[i+1][j-1].changeColor(fields[i][j].getColor());
                    }


                }

            }
        } else {
            if (temp[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i - 1][j + 1].isGrainColor())
                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getColor());
            if (temp[i + 1][j - 1].isGrainColor())
                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getColor());
        }



    }
}
