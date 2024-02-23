package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.Robot;

public class Diagnostics extends SubsystemBase {

    public Diagnostics(){}

    /**
     * getting the temp for the swerve module and checking to see if its too hot
     * @return isTooHot is a boolean
     */
    public boolean getDriveMotorTempCheck() {
        boolean isTooHot = false;
        if (Robot.swerveDrivetrain.fL.getDriveTemp() >= 80 ||
                Robot.swerveDrivetrain.fR.getDriveTemp() >= 80 ||
                Robot.swerveDrivetrain.rL.getDriveTemp() >= 80 ||
                Robot.swerveDrivetrain.rR.getDriveTemp() >= 80) {
            isTooHot = true;
        }
        return isTooHot;
    }

    /**
     * getting the temp of the indexer motor temp (indexer one and two)
     * @return Returns true or false
     */
    public boolean getIndexerMotorTempCheck() {
        boolean isTooHot = false;
        if (Robot.indexer.getIndexerOneMotorTemp() >= 80 ||
            Robot.indexer.getIndexerTwoMotorTemp() >= 80) {
            isTooHot = true;
        }
        return isTooHot;
    }

    /**
     * getting the temp of the shooter motor temp
     * @return returns true of false
     */
    public boolean getShooterMotorTempCheck() {
        boolean isTooHot = false;
        if (Robot.shooter.getShooterMotorTemp() >= 80) {
            isTooHot = true;
        }
        return isTooHot;
    }

    public boolean getSafe() {
        return Robot.shooter.getPosition() <= Robot.shooter.degreesToAbsolute(50);//(Robot.climber.getPosition() < 100 && Robot.elevator.getPosition() < 100 && Robot.shooter.getPosition() < 100);
    }
}
