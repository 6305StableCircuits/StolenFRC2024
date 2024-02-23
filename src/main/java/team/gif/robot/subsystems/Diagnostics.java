package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

public class Diagnostics extends SubsystemBase {

    public Diagnostics(){}

    /**
     * getting the temp for the swerve module and checking to see if its too hot
     * @return isTooHot is a boolean
     */
    public boolean getDriveMotorTempCheck() {
        if (Robot.swerveDrivetrain.fL.getDriveTemp() >= Constants.MotorTemps.DRIVETRAIN_MOTOR_TEMP ||
                Robot.swerveDrivetrain.fR.getDriveTemp() >= Constants.MotorTemps.DRIVETRAIN_MOTOR_TEMP ||
                Robot.swerveDrivetrain.rL.getDriveTemp() >= Constants.MotorTemps.DRIVETRAIN_MOTOR_TEMP ||
                Robot.swerveDrivetrain.rR.getDriveTemp() >= Constants.MotorTemps.DRIVETRAIN_MOTOR_TEMP) {
            return false;
        }
        return true;
    }

    /**
     * getting the temp of the indexer motor temp (indexer one and two)
     * @return Returns true or false
     */
    public boolean getIndexerMotorTempCheck() {
        if (Robot.indexer.getIndexerOneMotorTemp() >= Constants.MotorTemps.INDEXER_MOTOR_TEMP ||
            Robot.indexer.getIndexerTwoMotorTemp() >= Constants.MotorTemps.INDEXER_MOTOR_TEMP) {
            return false;
        }
        return true;
    }

    /**
     * getting the temp of the shooter motor temp
     * @return returns true of false
     */
    public boolean getShooterMotorTempCheck() {
        if (Robot.shooter.getShooterMotorTemp() >= Constants.MotorTemps.SHOOTER_MOTOR_TEMP) {
            return false;
        }
        return true;
    }

    public boolean isAMotorHot() {
        return getShooterMotorTempCheck() || getIndexerMotorTempCheck() || getDriveMotorTempCheck();
    }

    public boolean getSafeToDriveUnderStage() {
        return Robot.shooter.getPosition() <= Robot.shooter.degreesToAbsolute(50);//(Robot.climber.getPosition() < 100 && Robot.elevator.getPosition() < 100 && Robot.shooter.getPosition() < 100);
    }
}
