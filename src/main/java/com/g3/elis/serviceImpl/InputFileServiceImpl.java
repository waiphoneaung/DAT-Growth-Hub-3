package com.g3.elis.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.InputFile;
import com.g3.elis.repository.InputFileRepository;
import com.g3.elis.service.InputFileService;
import com.g3.elis.service.UserService;
import com.g3.elis.util.SheetData;

@Service
public class InputFileServiceImpl implements InputFileService {

	@Autowired
	private InputFileRepository inputFileRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileStorageConfig fileStorageConfig;

	@Override
	public String determineFileType(MultipartFile file) {
		String contentType = file.getOriginalFilename();
		if (contentType.contains(".mp4")) {
			return "video";
		} else if (contentType.contains(".png") || contentType.contains(".jpg")) {
			return "image";
		}
		return null;
	}

	@Override
	public void saveFile(MultipartFile file) {
		InputFile inputFile = new InputFile();
		inputFile.setFileName(file.getOriginalFilename());
		try {
			fileStorageConfig.saveFile(file, file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputFileRepository.save(inputFile);
	}

	@Override
	public List<InputFile> getAllInputFiles() {
		return inputFileRepository.findAll();
	}

	@Override
	public List<InputFile> getAllImageFiles() {
		List<InputFile> imageFiles = new ArrayList<>();
		for (InputFile inputFile : getAllInputFiles()) {
			if (inputFile.getFileName().contains(".jpg") || inputFile.getFileName().contains(".png")) {
				imageFiles.add(inputFile);
			}
		}
		return imageFiles;
	}

	@Override
	public List<InputFile> getAllVideoFiles() {
		List<InputFile> videoFiles = new ArrayList<>();
		for (InputFile inputFile : getAllInputFiles()) {
			if (inputFile.getFileName().contains(".mp4")) {
				videoFiles.add(inputFile);
			}
		}
		return videoFiles;
	}

	@Override
	public List<InputFile> getAllYouTubeUrl() {
		List<InputFile> YouTubeUrl = new ArrayList<>();
		for (InputFile inputFile : getAllInputFiles()) {
			if (!inputFile.getFileName().contains(".")) {
				YouTubeUrl.add(inputFile);
			}
		}
		return YouTubeUrl;
	}

	@Override
	public void saveYouTubeVideoFile(String url) {
		InputFile inputFile = new InputFile();

		String videoId = null;
		// https://www.youtube.com/watch?v=_Dd5RENCKLU
		if (url.contains("youtube.com")) {
			Pattern pattern = Pattern.compile("v=([^&]+)");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				videoId = matcher.group(1);
			}
		} else
			return;

		inputFile.setFileName(videoId);
		inputFileRepository.save(inputFile);
	}

	@Override
	public List<InputFile> getAllExcelFiles() {
		List<InputFile> ExcelFiles = new ArrayList<>();
		for (InputFile inputFile : getAllInputFiles()) {
			if (inputFile.getFileName().contains(".xlsx") || inputFile.getFileName().contains(".xlsm")) {
				ExcelFiles.add(inputFile);
			}
		}
		return ExcelFiles;
	}

	@Override
	public List<SheetData> readExcel(InputFile file) throws IOException {
		List<SheetData> allSheetsData = new ArrayList<>();

		File readFile = new File("./src/main/resources/static/upload-resources/" + file.getFileName());
		FileInputStream readFileInputStream = new FileInputStream(readFile);
		Workbook workbook = new XSSFWorkbook(readFileInputStream);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			List<List<String>> rows = new ArrayList<>();

			for (Row row : sheet) {
				List<String> cells = new ArrayList<>();

				for (Cell cell : row) {
					switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
					case STRING:
						cells.add(cell.getStringCellValue());
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							cells.add(new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));
						} else {
							double numericValue = cell.getNumericCellValue();
							if (numericValue == (long) numericValue) {
								cells.add(String.valueOf((long) numericValue));
							} else {
								cells.add(String.valueOf(cell.getNumericCellValue()));
							}
						}
						break;
					case BOOLEAN:
						cells.add(String.valueOf(cell.getBooleanCellValue()));
						break;
					case FORMULA:
						CellValue cellValue = formulaEvaluator.evaluate(cell);
						switch (cellValue.getCellType()) {
						case STRING:
							cells.add(cellValue.getStringValue());
							break;
						case NUMERIC:
							cells.add(String.valueOf(cellValue.getNumberValue()));
							break;
						case BOOLEAN:
							cells.add(String.valueOf(cellValue.getBooleanValue()));
							break;
						default:
							cells.add(" ");
							break;

						}
						break;
					case BLANK:
						cells.add("");
						break;
					default:
						cells.add("");
						break;
					}
				}
				rows.add(cells);
			}
			allSheetsData.add(new SheetData(sheet.getSheetName(), rows));
		}
		workbook.close();
		return allSheetsData;
	}

	@Override
	public void WriteEmployeeDataFromExcel(MultipartFile excelFile) throws IOException 
	{
		Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) 
		{
			boolean startCollectingData = false;
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			if (sheet.getSheetName().contains("Employee_Data") && sheet.getSheetName().equalsIgnoreCase("Employee_Data")) 
			{
				for (Row row : sheet) 
				{	
					UserDto userDto = new UserDto();
					for (Cell cell : row) 
					{
						if(getCellValue(formulaEvaluator,cell).contains("Sr.") || startCollectingData == true)
						{
							if(cell==null || getCellValue(formulaEvaluator,cell)=="") break;
							if(startCollectingData == false)
							{
								startCollectingData = true;
								break;
							}
							if(getCellValue(formulaEvaluator,cell).contains("Division"))
							{
								userDto.setDivision(getCellValue(formulaEvaluator,cell));
							}
							if(getCellValue(formulaEvaluator,cell).contains("25-") || getCellValue(formulaEvaluator,cell).contains("26-"))
							{
								userDto.setStaffId(getCellValue(formulaEvaluator,cell));
							}
							if(getCellValue(formulaEvaluator,cell).contains("Dept"))
							{
								userDto.setDept(getCellValue(formulaEvaluator,cell));
							}
							if(getCellValue(formulaEvaluator,cell).contains("@diracetechnology.com"))
							{
								userDto.setEmail(getCellValue(formulaEvaluator,cell));
							}
							if(getCellValue(formulaEvaluator,cell).equalsIgnoreCase("Active") || getCellValue(formulaEvaluator,cell).equalsIgnoreCase("InActive"))
							{
								userDto.setStatus(getCellValue(formulaEvaluator,cell));
							}
							if(getCellValue(formulaEvaluator,row.getCell(3))!=null)
							{
								userDto.setName(getCellValue(formulaEvaluator,row.getCell(3)));
							}
							if(getCellValue(formulaEvaluator,row.getCell(4))!=null)
							{
								userDto.setDoorLogNo(getCellValue(formulaEvaluator,row.getCell(4)));
							}
							if(getCellValue(formulaEvaluator,row.getCell(6))!=null)
							{
								userDto.setTeam(getCellValue(formulaEvaluator,row.getCell(6)));
							}
						}
					}
					if(userDto.getName()!=null)
					{
						userService.createUser(userDto);
					}
				}
			}
		}
		workbook.close();
	}
	
	private String getCellValue(FormulaEvaluator formulaEvaluator,Cell cell)
	{
		if(cell!=null)
		{
			switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return (new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));
				} else {
					double numericValue = cell.getNumericCellValue();
					if (numericValue == (long) numericValue) {
						return String.valueOf((long) numericValue);
					} else {
						return String.valueOf(cell.getNumericCellValue());
					}
				}
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				CellValue cellValue = formulaEvaluator.evaluate(cell);
				switch (cellValue.getCellType()) {
				case STRING:
					return cellValue.getStringValue();
				case NUMERIC:
					return String.valueOf(cellValue.getNumberValue());
				case BOOLEAN:
					return String.valueOf(cellValue.getBooleanValue());
				default:
					return "";
				}
			case BLANK:
				return "";
			default:
				return "";
			}
		}
		else
		{
			return "";
		}
	}
}

