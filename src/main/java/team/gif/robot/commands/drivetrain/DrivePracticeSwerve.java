package team.gif.robot.commands.drivetrain;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

public class DrivePracticeSwerve extends Command {
    private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;

    public DrivePracticeSwerve() {
        //Cap the maximum rate of change
        //hence the max accel.
        this.xLimiter = new SlewRateLimiter(Constants.ModuleConstants.TELE_DRIVE_MAX_ACCELERATION_UNITS_PER_SECOND);
        this.yLimiter = new SlewRateLimiter(Constants.ModuleConstants.TELE_DRIVE_MAX_ACCELERATION_UNITS_PER_SECOND);
        this.turningLimiter = new SlewRateLimiter(Constants.ModuleConstants.TELE_DRIVE_MAX_ANGULAR_ACCELERATION_UNITS_PER_SECOND);
        addRequirements(Robot.practiceDrivetrain);
    }

    @Override
    public void initialize() {
        System.out.println("INITIALIZED DRIVE COMMAND");
    }

    @Override
    public void execute() {
        //Get joystick
        double x = Robot.oi.driver.getLeftX();
        //if its not outside of the deadband then its 0
        x = (Math.abs(x) > Constants.DriveConstants.deadband) ? x : 0;
        double y = Robot.oi.driver.getLeftY();
        y = (Math.abs(y) > Constants.DriveConstants.deadband) ? y : 0;
        double rot = Robot.oi.driver.getRightX();
        rot = (Math.abs(rot) > Constants.DriveConstants.deadband) ? rot : 0;


        //when joystick is as max input (1)
        //we want the max speed we allow
        //1 * max speed = max speed
        //.5 * max speed = 50% of max speed
        x = xLimiter.calculate(x) * Constants.ModuleConstants.TELE_DRIVE_MAX_SPEED_METERS_PER_SECOND;
        y = yLimiter.calculate(y) * Constants.ModuleConstants.TELE_DRIVE_MAX_SPEED_METERS_PER_SECOND;
        rot = turningLimiter.calculate(rot) * Constants.ModuleConstants.TELE_DRIVE_MAX_ANGULAR_SPEED_RADIANS_PER_SECOND;
        System.out.println("x: " + x + "y: " + y + "rot: " + rot);
        //pass in velocity tells which wheels will should be at what angle and speed
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(x, y, rot);

        //converts the chassis speed to a swerve module state
        SwerveModuleState[] moduleStates = Constants.Drivetrain.DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);

        //set the states based on the current input to be on the actual robot
        Robot.practiceDrivetrain.setModuleStates(moduleStates);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.practiceDrivetrain.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}